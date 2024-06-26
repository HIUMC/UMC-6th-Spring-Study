# Chapter 2. AWS (VPC & Internet Gateway & EC2)

## 선행지식

**서브넷(subnet)** : IP주소에서 네트워크 영역을 부분적으로 나눈 부분 네트워크. 네트워크 장치들의 수에 따라 효율적으로 사용

**서브넷 마스크** : IP 주소 체계의 Network ID와 Host ID를 분리하는 역할을 함.

**서브넷팅** : IP주소 낭비를 방지하기 위해 원본 네트워크를 여러개의 서브넷으로 분리하는 과정. 서브넷 마스크의 비트 수를 증가시키는 것

**라우팅** : 패킷에 포함된 주소 등의 상세 정보를 이용하여 목적지까지 데이터 또는 메시지를 체계적으로 다른 네트워크에 전달하는 경로 선택, 그리고 스위칭하는 과정

**아이피 IPV4 vs IPV6**

IPV4(구) : 첫번째 인터넷 프로토콜. IP주소는 마침표로 구분된 4개의 숫자(10진수)로 표시. 32비트로 이루어진 주소(2진수) → 사용자 급증으로 주소 고갈 위기

IPV6(최신) : IPV4의 주소체계를 128비트 크기로 확장한 프로토콜 주소. 16비트씩 8자리로 각 자리는 콜론으로 구분 → 비용이 많이 들어 완전한 상용화 X

**고정 IP** : 변하지 않고 컴퓨터에 고정적으로 부여된 IP, 보안이 필요한 업체나 기관에서 사용

**유동 IP** : 변하는 IP, 일정주기동안 IP주소 임시로 발급, 대부분의 사용자

**공인 IP** : ISP와 같은 회사에 가입을 통해 IP를 제공받아 인터넷 사용

**사설 IP** : 어떤 네트워크 내부적으로 사용되는 고유한 주소, 공유기에 연결되어 있는 가정이나 회사의 각 네트워크 기기, 공인 IP와 달리 내부에서만 접근 가능

**사설망** : 사설 IP를 할당하여 그룹으로 묶는 방법. 지정된 대역의 IP만 사용 가능. 사설망이라는 개념을 사용하면 IP번호를 중복해서 마구 사용할 수 있어 IP의 절약이 가능하다.

**NAT(Network Adress Translation)** : 인터넷 주소 번역(사설IP→공인IP, 공인IP→사설IP)

**CIDR(Classless Inter-Domain Routing)** : 클래스 없는 도메인 간 라우팅 기법, 서브네팅 ⊂ CIDR

## AWS

**리전 :** 서비스를 위한 자원들을 여러 곳에 분산해서 배치해둔 것

**가용영역** : 리전을 한 번 더 분산 배치한 것

### <VPC>

가상의 네트워크 영역이므로 사설 아이피 주소를 가짐

**public subnet vs private subnet**

public subnet : Internet Gateway를 통해 서브넷을 퍼블릭 서브넷이 되게 하여 외부와 통신이 가능함(라우팅 테이블 설정을 해줘야함!)

<aside>
❓ **Destination : 10.0.0.0/16**

**Target  : Local**

</aside>

🤔 이게 무슨 뜻이지?

<aside>
💡 위의 라우팅이 의미하는 바는

**목적지 : 10.0.0.0/16 (VPC 주소 = VPC로 들어올 때)**

**Target : Local (내부 VPC 라우터가 알아서 잘 보내줄 것)**

이렇게 해석하시면 됩니다.

</aside>

private subnet : 아무런 조치를 취하지 않아 외부와 단절된 서브넷. 보안성. 외부와 통신을 원할 시 데이터 베이스를 사용하는 EC2 등과 같은 컴퓨팅 자원을 같은 VPC에 배치하면 된다

데이터가 잘 저장되었는지 DataGrip으로 확인하려 하였으나 원격접속이 불가능

→ private subnet과 같은 VPC에 존재하는 Public Subnet의 호스트의 도움을 받는다. 이때의 호스트를 Bastion host라고 함.

## 실습

### VPC 만들기

VPC생성

서브넷 생성

인터넷 게이트웨이 생성 후 VPC연결

라우팅 테이블 편집

보안그룹(방화벽) 생성

### EC2 생성하기

EC2 생성

탄력적 IP 설정

**탄력적 IP?** 인스턴스의 IG를 거쳐 통신 시 부여받을 IP주소를 고정시켜 종료된 후 다시 실행될 때 IP주소가 바뀌지 않게 해줌