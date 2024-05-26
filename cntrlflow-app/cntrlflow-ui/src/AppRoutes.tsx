import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./app/home/Login";
import Home from "./app/home/Home";

const AppRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />}></Route>
        <Route path="/home" element={<Home />}></Route>
      </Routes>
    </>
  );
};

export default AppRoutes;
