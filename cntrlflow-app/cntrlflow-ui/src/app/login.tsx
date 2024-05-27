import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import React, { useEffect, useState } from "react";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import Logo from "@/components/sidebar/_components/logo";
import { useLocation, useNavigate } from "react-router-dom";
import { HEADERS, LOGIN_URL } from "@/Constants";
import { checkAuth } from "@/utils/auth";
import { LocationState } from "@/utils/types";

const LoginForm: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");

  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);

  useEffect(() => {
    const fetchAuthStatus = async () => {
      const isAuthenticated = await checkAuth();
      setIsAuthenticated(isAuthenticated);
    };

    fetchAuthStatus();
  }, []);

  useEffect(() => {
    if (isAuthenticated) {
      // Redirect to the previous path if authenticated
      const from = (location.state as LocationState)?.from?.pathname || "/home";
      navigate(from, { replace: true });
    }
  }, [isAuthenticated, navigate, location.state]);

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleLogin = async () => {
    try {
      const response = await fetch(LOGIN_URL, {
        method: "POST",
        headers: HEADERS,
        body: JSON.stringify({ username, password }),
        credentials: "include",
      });

      if (response.ok) {
        const { status } = await response.json();
        if (status === "success") {
          navigate("/home", { replace: true });
        } else {
          setError("Invalid username or password");
        }
      }
    } catch (error) {
      setError("An error occurred. Please try again.");
    }
  };

  return (
    <div className="container flex h-screen w-screen flex-col items-center justify-center">
      <Card className="w-full max-w-sm">
        <CardHeader className="flex flex-col items-center">
          <div className="flex items-center mb-4 lg:gap-3 md:gap-3 sm:gap-3">
            <Logo />
            <span className="text-xl font-bold">CntrlFlow</span>
          </div>
          <CardDescription>
            <span>Welcome, Let&apos;s get started!</span>
          </CardDescription>
        </CardHeader>
        <CardContent className="grid gap-4">
          <div className="grid gap-2">
            <Label htmlFor="email">Username</Label>
            <Input
              id="username"
              type="text"
              placeholder="admin"
              value={username}
              onChange={handleUsernameChange}
              required
            />
          </div>
          <div className="grid gap-2">
            <Label htmlFor="password">Password</Label>
            <Input
              id="password"
              type="password"
              placeholder="********"
              value={password}
              onChange={handlePasswordChange}
              required
            />
          </div>
          {error && <p style={{ color: "red" }}>{error}</p>}
        </CardContent>
        <CardFooter>
          <span onClick={handleLogin}>
            <Button className="w-full">Sign in</Button>
          </span>
        </CardFooter>
      </Card>
    </div>
  );
};

export default LoginForm;
