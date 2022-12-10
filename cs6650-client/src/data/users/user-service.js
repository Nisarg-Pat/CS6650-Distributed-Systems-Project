import axios from "axios";
const USER_URL = "http://localhost:8080/user"
const api = axios.create();

export const signup = async (user) => {
    const response = await api.post(`${USER_URL}/signup`, user)
    return response.data;
}

export const login = async (user) => {
    const response = await api.post(`${USER_URL}/login`, user)
    return response.data;
}

export const logout = async (user) => {
    const response = await api.post(`${USER_URL}/logout`, user)
    return response.data;
}