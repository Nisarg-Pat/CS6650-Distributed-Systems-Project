import axios from "axios";
const BOOK_URL = "http://localhost:8080/cart"
const api = axios.create();

export const getCart = async (user) => {
    const response = await api.post(`${BOOK_URL}/usercart`, user)
    return response.data;
}

export const addToCart = async (cart) => {
    const response = await api.post(`${BOOK_URL}/addtocart`, cart);
    return response.data;
}

export const removeFromCart = async (cart) => {
    const response = await api.post(`${BOOK_URL}/remove`, cart);
    return response.data;
}

export const buyCart = async (cart) => {
    const response = await api.post(`${BOOK_URL}/buycart`, cart);
    return response.data;
}