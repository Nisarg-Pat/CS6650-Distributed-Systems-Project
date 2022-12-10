import axios from "axios";
const BOOK_URL = "http://localhost:8080/book"
const api = axios.create();

export const addBook = async (book) => {
    const response = await api.post(`${BOOK_URL}/addbook`, book)
    return response.data;
}

export const getBookListOfUser = async (user) => {
    const response = await api.post(`${BOOK_URL}/booklist`, user)
    return response.data
}

export const sellBook = async (book) => {
    const response = await api.post(`${BOOK_URL}/sell`, book)
    return response.data
}

export const shelfBook = async (book) => {
    const response = await api.post(`${BOOK_URL}/shelf`, book)
    return response.data
}

export const deleteBook = async (book) => {
    const response = await api.post(`${BOOK_URL}/delete`, book)
    return response.data
}

export const searchBook = async (search) => {
    const response = await api.get(`${BOOK_URL}/search/${search}`)
    return response.data
}