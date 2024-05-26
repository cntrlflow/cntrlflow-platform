import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";
import {
  persistStore,
  persistReducer,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from "redux-persist";
import storage from "redux-persist/lib/storage/session"; //for session storage
//import storage from "redux-persist/lib/storage"; //for local storage

// Configure Redux Persist
const persistConfig = {
  key: "root",
  storage,
};

// Define the initial state type
interface KeyValueState {
  data: { [key: string]: string };
}

const initialState: KeyValueState = { data: {} } as KeyValueState;

// Create a slice
const keySlice = createSlice({
  name: "keyValue",
  initialState,
  reducers: {
    setKeyValue: (
      state,
      action: PayloadAction<{ key: string; value: string }>
    ) => {
      const { key, value } = action.payload;
      state.data[key] = value;
    },
    deleteKeyValue: (state, action: PayloadAction<string>) => {
      const key = action.payload;
      delete state.data[key];
    },
    updateValue: (
      state,
      action: PayloadAction<{ key: string; value: string }>
    ) => {
      const { key, value } = action.payload;
      state.data[key] = value;
    },
    clearValues: () => initialState,
  },
});

// Export the actions
export const { setKeyValue, deleteKeyValue, updateValue, clearValues } =
  keySlice.actions;

const persistedReducer = persistReducer(persistConfig, keySlice.reducer);

// Create the store
const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});

// Initialize the persistor
const persistor = persistStore(store);

export { store, persistor, type KeyValueState };
