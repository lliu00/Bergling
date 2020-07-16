import axios from 'axios'
import Element from 'element-ui'


axios.defaults.baseURL = 'http://localhost:8081'

axios.interceptors.request.use(config => {
    return config;
})

axios.interceptors.response.use(resp => {
    let res = resp.data;

    if (res.code != '200') {
        Element.Message.error(res.msg, {duration: 3*1000});

        return Promise.reject(res.msg);
    } else {
        return resp;
    }

},
error => {
    console.log(error);

    if (error.response.status === 400) {
        store
    }
    })