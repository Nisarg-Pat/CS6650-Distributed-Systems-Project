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