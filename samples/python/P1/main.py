from flask import request

def main():
    req_body = request.data
    # decode req_body to string using .decode
    return "Request that we received is: "+req_body.decode("utf-8")
