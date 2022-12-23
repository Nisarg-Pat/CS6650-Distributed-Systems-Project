import axios from "axios";
const api = axios.create();

export const addBook = async (par) => {
    const response = await api.post(`${par.url}/book/addbook`, par.book)
    return response.data;
}

export const getBookListOfUser = async (par) => {
    const response = await api.post(`${par.url}/book/booklist`, par.user)
    return response.data
}

export const sellBook = async (par) => {
    const response = await api.post(`${par.url}/book/sell`, par.book)
    return response.data
}

export const shelfBook = async (par) => {
    const response = await api.post(`${par.url}/book/shelf`, par.book)
    return response.data
}

export const deleteBook = async (par) => {
    const response = await api.post(`${par.url}/book/delete`, par.book)
    return response.data
}

export const searchBook = async (par) => {
    const response = await api.get(`${par.url}/book/search/${par.search}`)
    return response.data
}