# 가상 파일 시스템
## 설계 과정
우선 인메모리 방식이 아니라 파일 시스템을 디스크에 저장하는 방식으로 구현해보자.
1. 초기화
초기화를 하면 루트 디렉토리가 생성된다.  
그리고 파일 시스템의 최대 크기를 지정해주어야 한다.  
파일이나 디렉토리를 새롭게 생성할 때마다 파일 시스템의 크기를 확인하여 공간이 충분한지 확인해야 한다.  
만약 남은 공간보다 파일의 크기가 크다면 파일을 생성할 수 없다.   
   (이걸 어떻게 할 수 가 있지? 그냥 현재 파일 시스템의 크기가 지정한 공간보다 커지면 더이상 추가를 못하게 하는 방식으로 하자.)
2. 메타데이터
메타데이터에는 우선 파일 시스템에 대한 정보, 파일에 대한 정보, 디렉토리에 대한 정보가 들어간다.  
파일과 디렉토리에 대한 정보는 이름, 크기, 종류, 부모 디렉토리 등이 들어간다.
   myfs.dat: 전체 디렉토리와 파일들이 저장되는 파일, 크기는 초기화된 최대 크기를 넘을 수 없다.  
   myfs.dir: 디렉토리 정보를 담고 있는 파일  
   myfs.info: 기타 필요한 추가적인 정보를 담고 있는 파일  
myfs.dat에는 파일 시스템의 크기, 파일 시스템에 저장된 파일의 개수, 디렉토리의 개수 등이 들어간다.  
파일이나 디렉토리를 추가할 때 마다 여기에 무조건 추가가 된다.  
myfs.info에는 파일의 실제 내용이 담겨있다. 
굳이 myfs.dir 파일이 필요할까? 
디렉토리에 필요한 정보 - 이름, 크기, 종류, 부모 디렉토리, 자식 디렉토리, 자식 파일, 절대주소
파일에 필요한 정보 - 이름, 크기, 종류, 부모 디렉토리, 내용, 절대주소
어차피 ,로 구분을 하고 있으니 csv 파일 형식으로 저장하자.  
결론 - dat과 info로만 나누자. 

## 필요 기능
1. 초기화
2. 디렉토리 생성
전체 디렉토리 중에서 만들려고 하는 이름의 디렉토리가 없는지를 확인하고, 없으면 생성한다.
3. 텍스트파일 생성
우선 경로에 있는 디렉토리가 실제 존재하는지를 찾는다. 
그리고 부모가 해당 디렉토리인 텍스트 파일 중에서 만들려고 하는 이름의 텍스트 파일이 없는지를 확인하고, 없으면 생성한다.
4. 디렉토리 읽기
우선 해당 디렉토리가 실제로 존재하는지 확인하고, 존재하면 해당 디렉토리를 부모로 하는 모든 파일을 찾아 출력한다. 
5. 텍스트 파일 읽기
우선 해당 파일이 실제로 존재하는지 확인하고 해당 파일을 찾아 내용을 출력한다. 

만들어야하는 메서드  
1. 디렉토리 찾기 - 해당 이름의 디렉토리 있는지 없는지 찾기, 해당 디렉토리를 부모 디렉토리로 갖고 있는 디렉토리 찾기 
2. 파일 찾기 - 해당 이름의 디렉토리 있는지 없는지 찾기, 해당 디렉토리를 부모 디렉토리로 갖고 있는 파일 찾기  

## 수정할 것
1. ,로 구분하기 때문에 ,입력이 들어가면 안된다.
2. 파일 시스템의 사이즈를 구현하지 못했다. 
3. 무조건 init을 하기 때문에, 나중에 저장된 파일이 없을 때만 init 하도록 하자.