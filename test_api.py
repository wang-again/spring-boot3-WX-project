import requests

try:
    response = requests.get('http://localhost:9090/getAllFeedbacks')
    print(f'状态码: {response.status_code}')
    print(f'响应内容: {response.text}')
except Exception as e:
    print(f'请求出错: {e}')
