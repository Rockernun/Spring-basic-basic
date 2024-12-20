## 스프링 핵심 원리 - 기본편 (인프런/김영한)

### 학습 기간 : 2024-10-02 ~ 

---

<details>
  <summary>스프링 핵심 원리 이해 1 - 예제 만들기</summary>
  <div markdown="1">
    <br />
    <p>
      회원 등급(Grade), 회원 엔티티(Member), 회원 저장소 인터페이스(MemberRepository), 메모리 회원 저장소 구현체(MemoryMemberRepository), 회원 서비스 인터페이스(MemberService), 회원 서비스 구현체(MemberServiceImpl), 회원 가입 main(MemberApp),
    회원 가입 테스트(MemberServiceTest) 도메인을 설계했다. 현재 회원 도메인 설계에서 인터페이스와 구현체에 모두 의존하는 문제점이 있지만, 일단 계속해서 주문과 할인 도메인을 개발했다.
    </p>
    <p>
      할인 정책 인터페이스(DiscountPolicy), 정액 할인 정책 구현체(FixDiscountPolicy), 
    주문 엔티티(Order), 주문 서비스 인터페이스(OrderService), 주문 서비스 구현체(OrderServiceImpl), 주문 할인 정책 실행(OrderApp), 주문 할인 정책 테스트(OrderServiceTest)를 개발했다.
    </p>
    <p>
      주문 생성 요청이 오면, 회원 정보를 조회하고, 할인 정책을 적용한 다음 주문 객체를 생성해서 반환한다. 메모리 회원 리포지토리와, 고정 금액 할인 정책을 구현체로 생성하도록 했다.
    </p>
    <br />
  </div>
</details>
<details>
  <summary>스프링 핵심 원리 이해 2 - 객체 지향 원리 적용</summary>
  <div markdown="2">
    <br />
    <p>
      정률 할인 정책 구현체(RateDiscountPolicy)를 원래의 정액 할인 정책 구현체를 대체하도록 했을 때, 할인 정책을 변경하려면 OrderServiceImpl를 수정해야 한다. 클래스 의존관계를 분석해 보면, 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있기 때문에 DIP 원칙을 위반하고 있다. 추가로, 현재 코드는 기능을 확장하거나 변경하면 클라이언트 코드에 영향을 주기 때문에 OCP 원칙 위반하고 있는 것이다. 즉, DIP를 위반하지 않도록 인터페이스에만 의존하게 의존관계를 변경해야 한다.
    </p>
    <p>
      OrderServiceImpl에서 인터페이스에만 의존하도록 코드를 변경했는데 실제로 실행하면 당연하게도 Null Exception이 뜬다. 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다. 애플리케이션을 하나의 공연이라고 생각했을 때, 남자 배우가 본인의 역할을 넘어 상대 여배우를 직접 초빙까지 하는 다양한 책임을 갖게 하는 것보다 각 배우들이 자신의 역할에만 충실할 수 있게 공연 기획자가 있었으면 좋겠다는 것이다.
    </p>
    <p>
      그래서 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스 AppConfig를 만든 것이다. 이 AppConfig에서는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성하고, 객체 인스턴스 참조를 생성자를 통해서 주입해준다. 이로써 MemberServiceImpl이 더 이상 MemoryMemberRepository를 의존하지 않고 MemberRepository에만 의존하도록 바뀌었다. MemberServiceImpl 입장에서는 생성자를 통해 어떤 구현 객체가 주입될지 알 수 없다. MemberServiceImpl 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정되는 것이다. 이제 MemberServiceImpl은 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다(DIP 완성).
    </p>
    <p>
      appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다. 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입이라고 한다.
    </p>
    <p>
      추가적으로, AppConfig에서 중복이 존재하고 역할에 따른 구현 명확하지 않아 리팩토링을 할 필요가 있었다. new MemoryMemberRepository 이 부분이 중복 제거되었다. 이제 MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다. AppConfig를 보면 역할과 구현 클래스가 한눈에 들어온다. 이제 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다
    </p>
    <br />
  </div>
</details>
<details>
  <summary>스프링 컨테이너와 스프링</summary>
  <div markdown="3">
  </div>
</details>
<details>
  <summary>싱글톤 컨테이너</summary>
  <div markdown="4">

  </div>
</details>
<details>
  <summary>컴포넌트 스캔</summary>
  <div markdown="5">

  </div>
</details>
<details>
  <summary>의존관계 자동 주입</summary>
  <div markdown="6">

  </div>
</details>
<details>
  <summary>빈 생명주기 콜백</summary>
  <div markdown="7">

  </div>
</details>
<details>
  <summary>빈 스코프</summary>
  <div markdown="8">

  </div>
</details>


