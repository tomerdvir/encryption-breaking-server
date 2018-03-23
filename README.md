# encryption-breaking-server
Boot application server for decrypting Xor text

A XOR cipher is an encryption method on which we take a text file, convert
the bytes to ASCII, then XOR each byte with a given value, taken from a
secret key. The advantage with the XOR function is that using the same
encryption key on the cipher text, restores the plain text; for example, 65
XOR 42 = 107, then 107 XOR 42 = 65.

This encryption breaking server gets an encrypted text and a key size - without the key itself - and works to find the encryption key using a brute-force lookup
