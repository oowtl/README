import axios from 'axios';

// axios 객체 생성
export default axios.create({
    baseURL: 'http://j5d104.p.ssafy.io',
    headers: {
        'Content-type': 'application/json'
    }
});
