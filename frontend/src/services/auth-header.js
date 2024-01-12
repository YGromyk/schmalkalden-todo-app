import authService, {API_URL} from "./auth.service";

const axios = require('axios');

export default async function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user.tokenExpirationDate <= (new Date().getTime() - 60 * 1000)) {
        await authService.refreshToken(localStorage.getItem('refreshToken'));
    }
    if (user && user.token) {
        return {Authorization: 'Bearer ' + user.token}; // for Spring Boot back-end
    } else {
        return {};
    }
}


export const axiosApiInstance = axios.create({
    baseURL: API_URL + "/api/"
});


function getUser() {
    return JSON.parse(localStorage.getItem('user'));
}


// Request interceptor for API calls
axiosApiInstance.interceptors.request.use(
    async config => {
        var user = getUser();
        var headers = {
            'Accept': 'application/json',
            'Access-Control-Allow-Origin': '*',
            "Content-Type": "application/json",
        };
        if (user.token) {
            console.log(user.token)
            headers['Authorization'] = `Bearer ${user.token}`;
        }
        console.log(headers);
        config.headers = headers;

        return config;
    },
    error => {
        Promise.reject(error)
    });


axiosApiInstance.interceptors.response.use((response) => {
    return response
}, async function (error) {
    const originalRequest = error.config;
    if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        const refreshResponse = await authService.refreshToken();
        saveToken(JSON.parse(localStorage.getItem('user')), refreshResponse);
        const access_token = refreshResponse.accessToken;
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + access_token;
        return axiosApiInstance(originalRequest);
    }
    return Promise.reject(error);
});


function saveToken(user, refreshTokenResponse) {
    try {
        user.token = refreshTokenResponse.accessToken;
        user.refreshToken = refreshTokenResponse.refreshToken;
        user.type = refreshTokenResponse.tokenType;
        user.tokenExpirationDate = refreshTokenResponse.expirationDate;
    } catch (error) {
        console.error(error);
    }

    localStorage.setItem('user', JSON.stringify(user));
}