# Chapter 3. Web Server & Web Application Server(WAS), Reverse Proxy

Web Application Server? 사용자가 많을 경우, 요청이 올 때마다 해당 요청에 적절한 컨텐츠를 만들어주는 서버(동적), WAS라고 부름

## Web Server 정적 콘텐츠 호스팅

### <NGINX에서의 정적콘텐츠 호스팅>

NGINX에는 설정 파일이 있는데, 웹 서버가 실행될 때 이 파일을 읽어 실행

```bash
cd /etc/nginx/sites-available
cat default
```

```bash
root /var/www/html;    //root는 정적 콘텐츠를 찾는 시작 디렉토리

index index.html index.htm index.nginx-debian.html;
//기본적인 요청에 대해 index 뒤의 파일들을 찾아 웹 상으로 보여준다.
```

```bash
location /temp{
                root /var/www;    // /var/www/temp
                index temp.html
                try_files $uri $uri/ =404;
 }  
 // /temp요청에 대해 중괄호 속 내용과 같이 하라는 뜻
```

### <Web Server VS Web Application Server(WAS)>

<aside>
💡 ***사실 [www.naver.com](http://www.naver.com)은 www.naver.com:80, 즉  웹 서버로 요청을 보내는 건데,***

***왜 응답은 WAS에서 주지????***

www.naver.com은 먼저 DNS를 통해 아이피 주소로 변환된다.

따라서 <네이버 서버 컴퓨터 ip주소 : 웹 서버의 프로세스가 부여 받은 포트전호> 모양으로 요청을 보냄

</aside>

### <Reverse Proxy>

클라이언트와 서버 간의 통신을 중계하고 보안, 성능 개선 등의 목적을 위해 중간에 위치하는 서버. 대리자로서 클라이언트의 요청을 우선 받고 본 서버로 보내준다.

포워드 프록시 vs 리버스 프록시

포워드 : 또 다른 외부 서버(다른 컴퓨터)로 요청을 보내줌

리버스 : 내부(같은 컴퓨터)의 다른 서버로 요청을 보내줌

```bash
location / {
                proxy_pass http://localhost:3000; <- 프록시 설정
                # First attempt to serve request as file, then
                # as directory, then fall back to displaying a 404.
                try_files $uri $uri/ =404;
        }
```