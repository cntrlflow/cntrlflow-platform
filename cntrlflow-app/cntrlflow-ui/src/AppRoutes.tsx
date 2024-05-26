import { Routes, Route, Navigate } from "react-router-dom";
import Login from "@/app/login";
import ProtectedRoute from "@/components/protected-route";
import Home from "@/app/home/home";
import Dashboard from "@/app/dashboard/dashboard";

const AppRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />}></Route>
        <Route path="/home" element={<ProtectedRoute redirectTo="/login" />}>
          <Route index element={<Home />} />
        </Route>
        <Route
          path="/dashboard"
          element={<ProtectedRoute redirectTo="/login" />}
        >
          <Route index element={<Dashboard />} />
        </Route>
      </Routes>
    </>
  );
};

export default AppRoutes;
