package hello.core.member;

public interface MemberService {
    // 회원 가입, 회원 조회 인터페이스
    void join(Member member);

    Member findMember(Long memberId);
}
