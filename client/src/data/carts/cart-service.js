import axios from "axios";
const api = axios.create();

export const getCart = async (par) => {
    const response = await api.post(`${par.url}/cart/usercart`, par.user)
    return response.data;
}

export const addToCart = async (par) => {
    const response = await api.post(`${par.url}/cart/addtocart`, par.cart);
    return response.data;
}

export const removeFromCart = async (par) => {
    const response = await api.post(`${par.url}/cart/remove`, par.cart);
    return response.data;
}

export const buyCart = async (par) => {
    const response = await api.post(`${par.url}/cart/buycart`, par.cart);
    return response.data;
}