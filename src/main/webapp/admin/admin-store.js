define(['js/api',"vue"],function (api,Vue) {
    class Store {
        constructor(initialState = {}) {
            this.states = {
                user_data:[],
                activity_data:[],
                group_data:[],
                news_data:[],
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
        addUser(states, data){
            states.user_data.push(data)
        },
        deleteUser(states, data){
            let index = states.user_data.indexOf(data)
            states.user_data.splice(index,1)
        },
        addNews(states, data){
            states.news_data.push(data)
        },
        deleteNews(states, data){
            let index = states.news_data.indexOf(data)
            states.news_data.splice(index,1)
        },
        addGroup(states, data){
            states.group_data.push(data)
        },
        deleteGroup(states, data){
            let index = states.group_data.indexOf(data)
            states.group_data.splice(index,1)
        },
        addActivity(states, data){
            states.activity_data.push(data)
        },
        deleteActivity(states, data){
            let index = states.activity_data.indexOf(data)
            states.activity_data.splice(index,1)
        }
        
        //addOrUpdateTask(states, data){
        //    // states.tasks[data.pid] = data;
        //    Vue.set(states.tasks,data.pid,data)
        //},
        //removeTask(states, pid){
        //    // delete states[id]
        //    Vue.delete(states.tasks,data.pid,data)
        //},
    }
    
    Store.prototype.getter = {
    }
    
    let c = Object.assign({},window.__INIT);
    const store = new Store(c);
    // 获取一些初始参数
    // 打算简单粗暴的将初始参数放在window 里面 在页面生成的时候由jsp填充
    return store;
});