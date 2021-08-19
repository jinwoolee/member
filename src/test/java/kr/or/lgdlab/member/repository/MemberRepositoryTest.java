package kr.or.lgdlab.member.repository;

import kr.or.lgdlab.common.SearchType;
import kr.or.lgdlab.member.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 회원 리스트 조회 (memberId 오름차순)
     */
    @Test
    void findByOrderByMemberidAsc() {
        List<Member> memberList = memberRepository.findByOrderByMemberidAsc();
        log.debug("memberList.size() : {}", memberList.size() );
        assertThat(memberList.size()).isEqualTo(11);
    }

    /**
     * 회원 찾기 (회원 아이디 LIKE '이름%')
     */
    @Test
    void findByMemberidStartingWith() {
        /*****GIVEN*****/
        int page = 0;
        int pageSize = 5;
        String searchType = SearchType.memberid.name();
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, searchType));

        String searchMemberid = "brown";

        /*****WHEN*****/
        List<Member> memberList = memberRepository.findByMemberidStartingWith(searchMemberid, pageRequest).toList();

        /*****THEN*****/
        assertThat(memberList.size()).isEqualTo(1);
    }

    /**
     * 회원 찾기 (이름 LIKE '이름%')
     */
    @Test
    void findByMembernmStartingWith() {
        /*****GIVEN*****/
        int page = 0;
        int pageSize = 5;
        String searchType = SearchType.membernm.name();
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, searchType));

        String searchMembernm = "브라";

        /*****WHEN*****/
        List<Member> memberList = memberRepository.findByMembernmStartingWith(searchMembernm, pageRequest).toList();

        /*****THEN*****/
        assertThat(memberList.size()).isEqualTo(1);
    }

    /**
     * 회원 찾기 (별칭 LIKE '별칭%')
     */
    @Test
    void findByAliasStartingWith() {
        /*****GIVEN*****/
        int page = 0;
        int pageSize = 5;
        String searchType = SearchType.alias.name();
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, searchType));

        String searchAlias = "곰";

        /*****WHEN*****/
        List<Member> memberList = memberRepository.findByAliasStartingWith(searchAlias, pageRequest).toList();

        /*****THEN*****/
        assertThat(memberList.size()).isEqualTo(2);
    }

    /**
     * 전체 멤버 조회
     */
    @Test
    void findAll() {
        /*****GIVEN*****/

        /*****WHEN*****/
        List<Member> memberList = memberRepository.findAll();

        /*****THEN*****/
        assertThat(memberList.size()).isEqualTo(11);
    }

    /**
     * 멤버 전체 카운트
     */
    @Test
    void count() {
        /*****GIVEN*****/

        /*****WHEN*****/
        long count = memberRepository.count();

        /*****THEN*****/
        assertThat(count).isEqualTo(11);
    }
}