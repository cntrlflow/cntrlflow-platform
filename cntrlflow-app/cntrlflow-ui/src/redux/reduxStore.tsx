import { useSelector } from "react-redux";
import {
  KeyValueState,
  deleteKeyValue,
  setKeyValue,
  updateValue,
  clearValues,
} from "./store";

export const GetReduxValue = (key: string) => {
  return useSelector((state: KeyValueState) => state.data[key]);
};

export const DeleteReduxValue = (
  dispatch: (arg0: {
    payload: string;
    type: "keyValue/deleteKeyValue";
  }) => void,
  key: string
) => {
  dispatch(deleteKeyValue(key));
};

export const AddReduxValue = (
  dispatch: (arg0: {
    payload: { key: string; value: string };
    type: "keyValue/setKeyValue";
  }) => void,
  key: string,
  value: string
) => {
  dispatch(setKeyValue({ key, value }));
};

export const UpdateReduxValue = (
  dispatch: (arg0: {
    payload: { key: string; value: string };
    type: "keyValue/updateValue";
  }) => void,
  key: string,
  value: string
) => {
  dispatch(updateValue({ key, value }));
};

export const ClearReduxStore = (
  dispatch: (arg0: { payload: undefined; type: "keyValue/clearValues" }) => void
) => {
  dispatch(clearValues());
};
