import axios from "axios";
const USER_URL = "http://localhost:8080/user"

export const signup = async (user) => {
    const response = await axios.post(`${USER_URL}/signup`, user)
    return response.data;
}

export const login = async (user) => {
    const response = await axios.post(`${USER_URL}/login`, user)
    return response.data;
}