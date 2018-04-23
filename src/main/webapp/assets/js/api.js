define(function () {
    // console.log('===')
    // let {remote} = config

    var stringifyPrimitive = function(v) {
        switch (typeof v) {
            case 'string':
                return v;

            case 'boolean':
                return v ? 'true' : 'false';

            case 'number':
                return isFinite(v) ? v : '';

            default:
                return '';
        }
    };

    function encode(obj, sep = "&", eq = "=", name) {
        if (obj === null) {
            obj = undefined;
        }

        if (typeof obj === 'object') {
            return Object.keys(obj).map(function(k) {
                var ks = encodeURIComponent(stringifyPrimitive(k)) + eq;
                if (Array.isArray(obj[k])) {
                    return obj[k].map(function(v) {
                        return ks + encodeURIComponent(stringifyPrimitive(v));
                    }).join(sep);
                } else if(obj[k] instanceof Date){
                    return ks + encodeURIComponent(obj[k])
                } else {
                    return ks + encodeURIComponent(stringifyPrimitive(obj[k]));
                }
            }).join(sep);

        }

        if (!name) return '';
        return encodeURIComponent(stringifyPrimitive(name)) + eq +
            encodeURIComponent(stringifyPrimitive(obj));
    }


    function paramSerialize (obj) {
        let str = [];
        for (let i of Object.keys(obj)) {
            str.push(encodeURIComponent(i) + "=" + encodeURIComponent(obj[i]));
        }
        return str.join("&");
    }

    function do_fetch(url, method, data, header = {}) {
        let d_headers = {
            'Pragma':'no-cache',
            'Cache-Control':'no-cache',
            'Content-Type':'application/x-www-form-urlencoded'
        }
        const headers = Object.assign({},d_headers,header)
        let fetchParams = {
            method: method,
            credentials: 'include',
            headers
        }
        if(data){
            if (method == 'GET') url += `?${encode(data)}`;
            if (method == 'POST') fetchParams.body = encode(data);
        }
        return fetch(url, fetchParams);
    }

    function get (url, data, header) { return do_fetch(url, "GET", data, header); }
    function post (url, data, header) { return do_fetch(url, "POST", data, header); }

    function nget(url, info,header) {
        return new Promise((resolve, reject)=>{
            get(url, info, header).then((resp) => {
                if (resp.ok) {
                    resolve(resp.json())
                } else {
                    reject(new Error(resp.statusText))
                }
            });
        })
    }

    function npost(url, info,header) {
        return new Promise((resolve, reject)=>{
            post(url, info,header).then((resp)=>{
                if(resp.ok){
                    resolve(resp.json())
                }else {
                    reject(new Error("服务器开小差了"))
                }
            });
        })
    }
    return {
        npost,
        nget,
        get,
        post,
        paramSerialize
        // importDirectory(directory,c){
        //     return nget(`${remote.root}/sys/!importDirectory`,{directory,c})
        // }
    };
});