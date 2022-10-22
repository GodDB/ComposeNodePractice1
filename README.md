## 컴포즈 노드 공부를 위한 프로젝트

## 구조 

     parent composer ----- layoutAppiler
            |
            |
       FrameLayoutNode ------ FrameLayout
            |                      |
      child composer ---- FrameLayoutAppiler
            |
            |--------------------------------
            |                               |
    FrameLayoutPropertyNode        FrameLayoutTextViewNode -- TextView


## 왜 만듬?
이거 응용하면 맵등을 다 컴포즈화 시킬 수 있음
