import { Routes, Route, Navigate } from "react-router-dom";
import Login from "@/app/Login";
import ProtectedRoute from "@/components/ProtectedRoute";
import Dashboard from "@/app/dashboard/Dashboard";
import Home from "./app/home/Home";

const AppRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />}></Route>
        <Route element={<ProtectedRoute />}>
          <Route path="/home" element={<Home />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Route>
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </>
  );
};

export default AppRoutes;
