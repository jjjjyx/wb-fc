!+(function () {
    require.config({
        baseUrl: './assets',
        paths: {
            vue: 'lib/vue.min',
            validator: 'lib/vue-validator.min',
            cookie: 'lib/js.cookie'/* ELEMENT: 'lib/element-ui'*/
        }
    })
    const modelList = ['vue', 'js/api', 'js/dom', 'validator', 'cookie']
    require(modelList,
        function (Vue, api, {on, once, addClass, removeClass}, validator, cookie) {
            Vue.use(validator, {strict: true})
            
            const app = new Vue({
                el: '#app',
                data: function () {
                    return {
                        form: 'in-form',
                        currentEmail: ''
                    }
                },
                components: {
                    InForm: {
                        template: `<form class="am-form" @submit.prevent="validateBeforeSubmit" style="position: relative">
                <div class="am-form-group am-form-icon" :class="{'am-form-error':errors.has('username')}">
                    <i class="am-icon-user"></i>
                    <input type="text" v-model="username" placeholder="输入你的账号"
                           v-validate="'required|min:3'" maxlength="30"
                           data-vv-delay="1000" name="username" class="am-form-field">
                    <span v-show="errors.has('username')" class="am-text-danger">{{ errors.first('username') }}</span></div>
                <div class="am-form-group am-form-icon">
                    <i class="am-icon-lock"></i> 
                    <input type="password"
                            v-model="password"
                            name="password"
                            placeholder="输入密码"
                            maxlength="18"
                            :class="{'am-form-error':errors.has('password')}"
                            v-validate="'required|alpha_num|max:18'"
                            class="am-form-field">
                    <span v-show="errors.has('password')" class="am-text-danger">{{ errors.first('password') }}</span>
                </div>
                <div class="am-form-group am-cf" v-if="showCode">
                        <div class="am-u-sm-4" style="padding: 0">
                            <input type="text" class="am-form-field" v-model="code" placeholder="请输入验证码"  v-validate="'required|alpha_num|min:4|max:4'" name="code">
                        </div>
                        <div class="am-u-sm-4">
                            <img src="./sign!vc" alt="" class="check-img" @click="refCode"  ref="checkImg">
                        </div>
                        <div></div>
                    </div>
                <div class="am-form-group">
                    <div class="checkbox"><label> <input type="checkbox" v-model="keep"> 记住十万年 </label></div>
                </div>
                <div class="button-group am-margin-top-xl">
                    <button type="submit" class="am-btn am-btn-secondary">sign in</button>
                </div>
                <div class="loading-dimmer" v-if="loading"><i class="am-icon-spinner am-icon-pulse"></i></div>
            </form>`,
                        data () {
                            return {
                                username: '',
                                password: '',
                                code: '',
                                loading: true,
                                errorCount: 0,
                                keep: false
                            }
                        },
                        methods: {
                            refCode () {
                                this.$refs.checkImg.src = './sign!vc?_=' +
                                    (+new Date())
                            },
                            validateBeforeSubmit () {
                                this.$validator.validateAll().then((v) => {
                                    if (v) {
                                        let p = {
                                            username: this.username,
                                            password: this.password
                                        }
                                        if (this.showCode) p.code = this.code
                                        api.npost('./sign!in', p).
                                            then(({code, data}) => {
                                                if (code == 0) {
                                                    if (this.keep) {
                                                        cookie.set('keep_me',
                                                            true, {
                                                                expires: 7,
                                                                path: config.remote.root
                                                            })
                                                        cookie.set('un',
                                                            this.username, {
                                                                expires: 7,
                                                                path: config.remote.root
                                                            })
                                                        cookie.set('up',
                                                            this.password, {
                                                                expires: 7,
                                                                path: config.remote.root
                                                            })
                                                    } else {
                                                        cookie.remove('keep_me')
                                                        cookie.remove('un')
                                                        cookie.remove('up')
                                                    }
                                                    window.location.href = './admin/'
                                                } else {/* */
                                                    alert('账号密码错误')
                                                    this.errorCount++
                                                    if (this.errorCount >=
                                                        3) this.showCode = true
                                                    document.querySelector(
                                                        '[name="password"]').
                                                        focus()
                                                }
                                            })
                                    } else {
                                        alert('请完成登陆验证')
                                    }
                                })
                            }
                        },
                        props: {
                            showCode: Boolean
                        },
                        mounted () {
                            this.$nextTick(() => {
                                this.loading = false
                            })
                        },
                        created () {
                            let keep = cookie.get('keep_me')
                            if (keep && keep == 'true') {
                                this.keep = true
                                this.username = cookie.get('un')
                                this.password = cookie.get('up')
                            }
                        }
                    },
                    upForm: {
                        template: `
<form @submit.prevent="validateBeforeSubmit" class="am-form am-form-horizontal">
    <div class="am-form-group">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >电子邮箱</label>
        <div class="am-u-sm-9">
            <input type="email" class="am-form-field" v-model="email" placeholder="请输入你的电子邮箱作为账户名" 
            v-validate="'required|email|checkAccount:email'" name="email" data-vv-delay="500">
            <span v-show="errors.has('email')" class="am-text-danger">{{ errors.first('email') }}</span>
        </div>
        
    </div>
    <div class="am-form-group  am-form-icon am-form-feedback">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label">验证码</label>
        <div class="am-u-sm-5">
            <input type="text" class="am-form-field" v-model="code" placeholder="请输入验证码"  v-validate="'required|alpha_num|min:4|max:4'" name="code">
            <!--<span class="am-icon-check"></span>-->
            <span v-show="errors.has('code')" class="am-text-danger">{{ errors.first('code') }}</span>
        </div>
        <div class="am-u-sm-4">
            <img src="./sign!vc" alt="" class="check-img" @click="refCode" ref="checkImg">
        </div>
    </div>
    <div class="am-form-group">
        <div  class="am-u-sm-9 am-u-sm-offset-3">
            <input type="checkbox" checked="checked"> 阅读并接受<a >《用户协议》</a>及<a >《隐私权保护声明》</a>
        </div>
    </div>
    <div class="am-form-group">
        <div class="am-u-sm-3 am-u-sm-offset-3">
              <button type="submit" class="am-btn am-btn-secondary am-btn-sm" :disabled="errors.any()">下一步</button>
        </div>
        
    </div>

        <div class="am-modal" tabindex="-1" ref="model" :class="{'am-modal-active am-block':show}" >
               <div class="am-modal-dialog">
                <div class="am-modal-hd">服务协议及隐私权政策
                  <a href="javascript: void(0)" class="am-close am-close-spin" @click="close(false)">&times;</a>
                </div>
                <div class="am-modal-bd am-text-left">
                    <p>其明重视用户的隐私，隐私权是您重要的权利。您在使用我们的服务时，我们可能会收集和使用您的相关信息。我们希望通过本《隐私政策》向您说明，在使用我们的服务时，我们如何收集、使用、储存和分享这些信息，以及我们为您提供的访问、更新、控制和保护这些信息的方式。本《隐私政策》与您所使用的其明服务息息相关，希望您仔细阅读，在需要时，按照本《隐私政策》的指引，作出您认为适当的选择。本《隐私政策》中涉及的相关技术词汇，我们尽量以简明扼要的表述，并提供进一步说明的链接，以便您的理解。</p>
                    <p>您使用或继续使用我们的服务，即意味着同意我们按照本《隐私政策》收集、使用、储存和分享您的相关信息。</p>
                    <p>如对本<a >《隐私政策》</a>或相关事宜有任何问题，请通过xxx@qq.com与我们联系。</p>
                    
                    <div class="loading-dimmer" v-if="loading">
                        <i class="am-icon-spinner am-icon-pulse"></i>                
                    </div>
                </div>
                <div class="am-modal-footer">
                  <span class="am-modal-btn" @click="close(false)">取消</span>
                  <span class="am-modal-btn" @click="close(true)">确定</span>
                </div>
              </div>
        </div>

    <div ref="dimmer" class="am-dimmer " :class="{'am-active am-block':show}"></div>   
</form>`,
                        data () {
                            return {
                                show: false,
                                loading: true,
                                email: '',
                                code: ''
                            }
                        },
                        methods: {
                            validateBeforeSubmit () {
                                this.$validator.validateAll().then((v) => {
                                    if (v) {
                                        api.npost('./sign!up', {
                                            email: this.email,
                                            code: this.code
                                        }).then((v) => {
                                            if (v.code == 0) {
                                                this.$parent.currentEmail = v.data.email
                                                this.$parent.form = 'info-form'
                                                
                                            } else if (v.code >= 2) {
                                                alert(v.msg)
                                            }
                                            if (v.code == 4) this.refCode()
                                        })
                                    }
                                })
                            },
                            refCode () {
                                this.$refs.checkImg.src = './sign!vc?_=' +
                                    (+new Date())
                            },
                            close (v) {
                                if (!v) this.$parent.form = 'in-form'
                                this.show = false
                            }
                        },
                        created () {
                        
                        },
                        beforeDestroy () {
                            if (this.$refs.model.parentNode == document.body) {
                                document.body.removeChild(this.$refs.model)
                            }
                            if (this.$refs.dimmer.parentNode == document.body) {
                                document.body.removeChild(this.$refs.dimmer)
                            }
                        },
                        mounted () {
                            document.body.appendChild(this.$refs.model)
                            document.body.appendChild(this.$refs.dimmer)
                            
                            this.show = true
                            let modelbody = this.$refs.model.childNodes[0]
                            addClass(modelbody, 'animated fadeInDown')
                            once(modelbody, 'animationend', () => {
                                removeClass(modelbody, 'animated fadeInDown')
                                this.loading = false
                            })
                        }
                    },
                    infoForm: {
                        template: `
<form @submit.prevent="validateBeforeSubmit" class="am-form-inline info-form">
     <legend>登录信息 - {{currentEmail}}</legend>
     <div class="am-form-group" :class="{'am-form-error':errors.has('username')}">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >登录名</label> 
        <div class="am-u-sm-9 " style="padding-left: 1em">
            <input type="text" class="am-form-field"  placeholder="登录名称" name="username" v-model="params.username" data-vv-delay="500"
            v-validate="'required|alpha_dash|min:3|max:18|checkAccount:username'">
            <!--<span v-show="errors.has('username')" class="am-text-danger">{{ errors.first('username') }}</span>-->
        </div>
    </div>  <span style="font-size: 12px"> 设置一个登录名，方便登录</span> 
    <br>
    <br>
     <div class="am-form-group" :class="{'am-form-error':errors.has('password')}">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >登录密码</label> 
        <div class="am-u-sm-9"  style="padding-left: 1em">
            <input type="password" class="am-form-field"  placeholder="设置一个密码吧" name="password" v-model="params.password"
                v-validate="'required|alpha_num|min:6|max:18'">
            <!--<span v-show="errors.has('password')" class="am-text-danger">{{ errors.first('password') }}</span>-->
        </div>
    </div>
    <div class="am-form-group" :class="{'am-form-error':errors.has('c_p')}">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >确认密码</label> 
        <div class="am-u-sm-9 " style="padding-left: 1em">
            <input type="password" class="am-form-field"  placeholder="确认您的输入" name="c_p" v-model="params.c_p"
                v-validate="'required|confirmed:password'">
            <!--<span v-show="errors.has('c_p')" class="am-text-danger">{{ errors.first('c_p') }}</span>-->
        </div>
    </div>
    <legend class="am-margin-top-sm">基本信息</legend>
    <div class="am-form-group">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >真实姓名</label> 
        <div class="am-u-sm-9 " style="padding-left: 1em">
            <input type="text" class="am-form-field"  placeholder="显示的名称" name="nickname" v-model="params.nickname">
            <!--<span v-show="errors.has('email')" class="am-text-danger">{{ errors.first('email') }}</span>-->
        </div>
    </div>
    <div class="am-form-group" :class="{'am-form-error':errors.has('phone')}">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >手机</label> 
        <div class="am-u-sm-9 " style="padding-left: 1em">
            <input type="number" class="am-form-field"  placeholder="您的手机号" name="phone" v-model="params.phone"
            v-validate="'phone'">
            <!--<span v-show="errors.has('email')" class="am-text-danger">{{ errors.first('email') }}</span>-->
        </div>
    </div>
    <br><br>
    <div class="am-form-group" :class="{'am-form-error':errors.has('qq')}">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >QQ</label> 
        <div class="am-u-sm-9 " style="padding-left: 1em">
            <input type="number" class="am-form-field"  placeholder="QQ"  name="qq" v-model="params.qq"
            v-validate="'QQ'">
            <!--<span v-show="errors.has('email')" class="am-text-danger">{{ errors.first('email') }}</span>-->
        </div>
    </div>
    <div class="am-form-group">
        <label for="doc-ipt-3" class="am-u-sm-3 am-form-label" >性别</label> 
        <div class="am-u-sm-9 " style="padding-left: 1em;width: 156px;">
            <label class="am-radio-inline">
            <input type="radio" name="sex" value="男" v-model="params.sex"> 男
          </label>
          <label class="am-radio-inline">
            <input type="radio" name="sex" value="女" v-model="params.sex"> 女
          </label>
            <!--<span v-show="errors.has('email')" class="am-text-danger">{{ errors.first('email') }}</span>-->
        </div>
    </div>
    <br><br>
    <div class="am-text-center">
        <button type="submit" class="am-btn am-btn-secondary am-btn-sm" >完成</button>
    </div>
    
</form>
                     `,
                        data () {
                            return {
                                params: {
                                    email: '',
                                    username: this.$parent.currentEmail,
                                    password: '',
                                    c_p: '',
                                    nickname: '',
                                    phone: '',
                                    qq: '',
                                    sex: '男'
                                }
                            }
                        },
                        methods: {
                            validateBeforeSubmit () {
                                this.$validator.validateAll().then((v) => {
                                    if (v) {
                                        api.npost('./sign!up_fill',
                                            this.params).then((v) => {
                                            if (v.code == 0) {
                                                window.location.href = './data/'
                                            } else {
                                                alert(v.msg)
                                            }
                                        })
                                    }
                                })
                            }
                        },
                        
                        computed: {
                            currentEmail () {
                                return this.$parent.currentEmail ||
                                    this.$slots.default[0].text.trim()
                            }
                        },
                        created () {
                            console.log(this.$slots.default)
                            if (!this.$parent.currentEmail &&
                                !this.$slots.default) {
                                this.$parent.form = 'in-form'
                            } else {
                                this.params.email = this.currentEmail
                                this.params.username = this.currentEmail
                            }
                        },
                        mounted () {
                            
                            const phone = {
                                getMessage: (field, ...v) => {
                                    return `不是一个正确的电话号码`
                                },
                                validate: (
                                    value, ...v) => /^1[35847]\d{9}$/.test(
                                    value)
                            }
                            const qq = {
                                getMessage: (field, ...v) => {
                                    return `不是一个正确的电话号码`
                                },
                                validate: (value, ...v) => /^\d{5,11}$/.test(
                                    value)
                            }
                            validator.Validator.extend('phone', phone)
                            validator.Validator.extend('QQ', qq)
                        }
                    }
                },
                computed: {},
                created () {
                    let hash = location.hash.replace('#', '') || ''
                    const views = ['in-form', 'up-form', 'info-form']
                    
                    if (hash && views.indexOf(hash) >= 0) {
                        this.form = hash
                    }
                    
                },
                methods: {},
                mounted () {
                    const checkEmail = {
                        getMessage: (field, args) => {
                            return `The ${args} has been registered !`
                        },
                        validate: function (value, check) {
                            return new Promise((resolve) => {
                                api.nget('./sign!checkAccount', {value, check}).
                                    then(({code, data}) => {
                                        if (code == 0) {
                                            resolve(false)
                                        } else {
                                            resolve(true)
                                        }
                                    })
                            })
                        }
                    }
                    validator.Validator.extend('checkAccount', checkEmail)
                }
            })
        })
    
})()