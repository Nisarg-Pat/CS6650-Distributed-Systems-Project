import axios from "axios";
const api = axios.create();

export const signup = async (par) => {
    const response = await api.post(`${par.url}/user/signup`, par.newUser)
    return response.data;
}

export const login = async (par) => {
    const response = await api.post(`${par.url}/user/login`, par.newUser)
    return response.data;
}

export const logout = async (par) => {
    const response = await api.post(`${par.url}/user/logout`, par.newUser)
    return response.data;
}