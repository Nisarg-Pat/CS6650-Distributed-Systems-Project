import {createSlice} from "@reduxjs/toolkit";

const serverReducer = createSlice({
    name: 'users',
    initialState: {
        server: {
            host: "localhost",
            port: "8080",
            url: "http://localhost:8080"
        }
    },
    reducers:{
        setServer (state, action) {
            state.server = action.payload;
            console.log("Server set to: "+action.payload.url);
        }
    }
})

export const {setServer} = serverReducer.actions;
export default serverReducer.reducer;