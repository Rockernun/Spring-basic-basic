package hello.core.member;

public interface MemberRepository {
    // MemberRepository 인터페이스
    void save(Member member);

    Member findById(Long memberId);
}
