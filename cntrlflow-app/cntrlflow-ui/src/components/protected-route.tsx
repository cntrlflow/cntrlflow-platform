import React, { useEffect, useState } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { checkAuth } from "../utils/auth";

const ProtectedRoute: React.FC = () => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);

  useEffect(() => {
    const verifyAuth = async () => {
      const isAuthenticated = await checkAuth();
      setIsAuthenticated(isAuthenticated);
    };

    verifyAuth();
  }, []);

  if (isAuthenticated === null) {
    return <div>Loading...</div>;
  }

  // If user is already authenticated, redirect to the current location
  if (
    isAuthenticated &&
    (location.pathname === "/login" || location.pathname === "/")
  ) {
    window.location.href = location.pathname;
    return null; // Prevent rendering while redirecting
  }

  return isAuthenticated ? <Outlet /> : <Navigate to="/login" replace />;
};

export default ProtectedRoute;
