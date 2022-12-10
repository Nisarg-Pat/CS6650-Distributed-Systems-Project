import {createSlice} from "@reduxjs/toolkit";
import {addBookThunk, bookListThunk} from "./books-thunk";

const booksReducer = createSlice({
        name: 'books',
        initialState: {
            booksList: []
        },
        reducers:{
        },
        extraReducers: {
            [addBookThunk.fulfilled]: (state, action) => {
                state.booksList.push(action.payload)
                console.log(state.booksList)
            },
            [bookListThunk.fulfilled]: (state, action) => {
                state.booksList = action.payload
                console.log(state.booksList)
            }
        }
    }
)

export default booksReducer.reducer;