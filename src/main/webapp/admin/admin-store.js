define(['js/api',"js/lib/vue.min"],function (api,Vue) {
    console.log(api);
    class Store {
        constructor(initialState = {}) {
            this.states = {
                config:{},
                map:{},
                tasks:{},
                index_info:{}
            }
            
            for (let prop in initialState) {
                if (initialState.hasOwnProperty(prop) && this.states.hasOwnProperty(prop)) {
                    this.states[prop] = initialState[prop];
                }
            }
        }
        
        commit(name, ...args) {
            const mutations = this.mutations;
            if (mutations[name]) {
                mutations[name].apply(this, [this.states].concat(args));
            } else {
                throw new Error(`Action not found: ${name}`);
            }
        }
        
        setStatus(status) {
            this.states._rollbackStatus = this.states.status;
            this.states.status = status;
        }
    };
    
    Store.prototype.mutations = {
        addOrUpdateTask(states, data){
            // states.tasks[data.pid] = data;
            Vue.set(states.tasks,data.pid,data)
        },
        removeTask(states, pid){
            // delete states[id]
            Vue.delete(states.tasks,data.pid,data)
        },
    }
    
    Store.prototype.getter = {
    }
    
    let c = Object.assign({},window.__INIT);
    const store = new Store(c);
    // 获取一些初始参数
    // 打算简单粗暴的将初始参数放在window 里面 在页面生成的时候由jsp填充
    return store;
});