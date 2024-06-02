// Define your constants as TypeScript variables
export const AUTH_HEADER: string = "Basic Y250cmxmbG93OmNudHJsZmxvdw==";
export const LOGIN_URL: string = "/api/auth/login";
export const VALIDATE_URL: string = "/api/auth/validate";
export const LOGOUT_URL: string = "/api/auth/logout";
export const HEADERS = {
  authorization: AUTH_HEADER,
  "Content-Type": "application/json; charset=utf8",
  "Cache-Control":
    "no-cache, no-store, must-revalidate, post-check=0, pre-check=0",
  Pragma: "no-cache",
  Expires: "-1",
  "X-Frame-Options": "SAMEORIGIN",
  "X-Content-Type-Options": "nosniff",
};
