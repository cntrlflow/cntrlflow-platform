# Generate Certificates

openssl genpkey -algorithm RSA -out localhost.key
openssl req -new -key localhost.key -out localhost.csr
openssl x509 -req -days 365 -in localhost.csr -signkey localhost.key -out localhost.crt
