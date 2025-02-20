# HEAP메모리

## 설계하기
### heap 객체
heap 객체 안에는 heap에게 할당된 총 메모리가 있다. 
heap 객체를 선언할때 base 메모리를 설정하도록 했다.  
그리고 이제 init을 하면 실제로 사용할 수 있는 메모리가 결정되고 base 메모리 주소를 반환한다.  
실제로 사용할 메모리는 배열로 구현하고, 배열 한 칸을 한 바이트라고 생각한다.  

heap 객체에는 malloc을 통해 여러 블록들이 추가된다.  
맨 처음 init을 하면 heap전체가 하나의 큰 free 블록이 된다.  
malloc을 실행하며 여러 블록으로 나뉘게 된다.  

각 블록의 맨 처음에는 heap의 메타데이터가 포함되어야 한다.  
메타데이터에는 블록의 크기, 타입, 할당여부가 포함된다.  
구현하면서 블록의 메타데이터엔 이전, 이후 블록의 시작 offset을 포함하는 것이 필요하다고 느꼈다. 
메타데이터 역시 8의 배수여야했고, 많은 정보가 필요 없기에 그냥 8바이트로 설정했다.  
### setSize
setSize는 단순히 그냥 map으로서 고유한 사이즈를 지정하고, typeSizeMap과 typeIdMap을 통해 관리한다.  
새로운 type을 추가할 때마다 map에 추가한다.  
### malloc
블록에는 우선 메타데이터가 있다. 메타데이터에는 블록의 크기와 할당여부, 현재 블록의 타입이 있다.  
배열을 확보하기 위해 메모리를 읽으면, 우선 메타데이터를 처음 읽게 된다.  

malloc을 하면 우선 배열을 확보해야한다.
heap의 시작에서부터 빈칸이 있는지를 확인하고, 빈칸이 있다면 그 빈칸을 할당한다.  
메타데이터를 읽어, 현재 블록이 할당이 가능하다면, 할당을하고, 아니라면 블록의 사이즈만큼 넘어가, 다음 메타데이터로 넘어가게된다.
할당한 후, 이후의 메타데이터를 만들어준다. 기존의 블록을 두개로 나눈다고 생각.
그렇게 하기 위해선 기존의 메타데이터를 기반으로 두번째 메타데이터를 만들어주면 된다.
 
### free 
할당된 블록을 할당 해제한다.  
isAllocated를 0으로 변경하고, 이전과 이후의 블록이 이미 할당 해제되어 있다면 그 블록들과 합쳐준다.

### 메타데이터 양식
1칸은 size,
1칸은 할당여부,
1칸은 타입,
5칸은 padding

## 주요 메서드 
```java
    public int malloc(String type, int count){
        int size = typeSizeMap.get(type)>=TYPE_SIZE ? typeSizeMap.get(type)*count: TYPE_SIZE*count;
        int offset = findFreeBlock(size);
        if (offset == -1) throw new IllegalArgumentException("할당 가능한 메모리가 없습니다.");
        // 원래 블록의 사이즈
        int originBlockSize = memory[offset];
        // freeBlock을 찾고, 그 부분에 새로운 메타데이터 및 데이터 생성
        createMetaData(offset, size,true,type);
        int dataStartIdx = offset+META_DATA_SIZE;
        for(int i = 0 ; i < count; i++){
            allocateOneByteAtOffset(dataStartIdx,type);
            dataStartIdx+=8;
        }
        // 데이터 끝에 원래 메타데이터가 있었다면 그대로 놔두고, 그렇지 않다면 새로운 메타데이터 생성
        if(originBlockSize !=size){
            createMetaData(offset+size+META_DATA_SIZE,originBlockSize - size - META_DATA_SIZE,false,"");
        }
        return offset;
    }
```
우선 type의 size가 8보다 작다면 8로 설정하여 이 블록에 할당될 크기를 결정한다.  
그리고 아래의 findFreeBlock 메서드를 통해 size가 할당될 수 있는 크기의 블록을 찾는다.  
그 블록에 새로운 메타데이터와 데이터를 생성한다.  
그 후 데이터 끝에 원래 메타데이터가 있었다면 그대로 놔두고, 그렇지 않다면 새로운 메타데이터를 생성한다.  
새로운 메타데이터는 빈 블록을 나타낸다. 

```java
    private int findFreeBlock(int size){
        int offset = 0 ;
        while(offset< memory.length){
            int blockSize = memory[offset];
            int isAllocated = memory[offset+1];
            if (isAllocated == 0 && blockSize>=size){
                return offset;
            }
            offset += blockSize + META_DATA_SIZE;
        }
        return -1;
    }
```
size가 들어갈 수 있는 블록을 찾는 메서드이다.  
offset을 0부터 시작하여, 블록의 크기와 할당여부를 확인한다.
만약 할당되지 않은 블록이고, size보다 크다면 그 블록의 시작 offset을 반환한다.
만약 할당이 불가능한 블록이라면 다음 블록을 찾는다.  
다음 블록을 찾는 방법은 메타데이터의 크기 8과 해당 블록의 크기를 더하면 다음 블록의 시작점이 나온다.  
```java
    public void free(int pointer){
        //isAllocated를 false로 변경
        memory[pointer+1] = 0;
        int size = memory[pointer];
        int nextBlockStart = pointer + size + META_DATA_SIZE;
        // 만약 다음 블록이 사용중이지 않다면 하나의 블록으로 합쳐줌
        if (memory[nextBlockStart+1] == 0 ){
            memory[pointer] = size + memory[nextBlockStart] + META_DATA_SIZE;
        }
    }
```
해당 포인터의 블록을 할당해제한다.  
할당을 해제하면 다음에 다른 size의 블록이 할당할 수 있도록 다른 블록과 합쳐줘야한다.  
그래서 할당을 해제할 때, 다음 블록도 할당이 해제되어 있다면, 두 블록을 합쳐준다.  
그렇게 빈 두개의 블록을 합쳐야 큰 사이즈의 블록도 할당해줄 수 있기 때문이다. 

## 수정해야 할 것 
다 끝냈다고 생각했는데, 마지막으로 free를 할때, 다음 블록과는 합치는 로직이 있지만, 이전 블록과는 합치는 로직이 없다.  
이 부분 수정하면 좋을 것 같은데, 메타데이터 양식에 이전 블록의 시작 위치를 넣어야해서 좀 할 게 많다.  
그래서 못할 수도 있다.  
