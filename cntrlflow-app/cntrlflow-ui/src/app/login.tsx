import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import React, { useState } from "react";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import Logo from "@/components/sidebar/_components/logo";
import { useNavigate } from "react-router-dom";
import { AUTH_HEADER, LOGIN_URL } from "@/Constants";
import { AddReduxValue } from "@/redux/reduxStore";
import { useDispatch } from "react-redux";

const LoginForm: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  // const authenticated = GetReduxValue("authenticated");
  // const previousPath = GetReduxValue("previousPath");

  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");

  // useEffect(() => {
  //   if (authenticated == "true") {
  //     if (previousPath != "") {
  //       navigate(previousPath);
  //     }
  //   }
  // }, [authenticated, navigate, previousPath]);

  const headers = {
    authorization: AUTH_HEADER,
    "Content-Type": "application/json; charset=utf8",
  };

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleButtonClick = async (
    event: React.MouseEvent<HTMLAnchorElement, MouseEvent>
  ) => {
    event.preventDefault();
    try {
      const response = await fetch(LOGIN_URL, {
        method: "POST",
        headers: headers,
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();

      if (response.ok && data.status === "success") {
        AddReduxValue(dispatch, "authenticated", "true");
        navigate("/home");
      } else {
        setError(data.message || "Invalid username or password");
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
          <span onClick={handleButtonClick}>
            <Button className="w-full">Sign in</Button>
          </span>
        </CardFooter>
      </Card>
    </div>
  );
};

export default LoginForm;
