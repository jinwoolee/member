package kr.or.lgdlab.member.service;

import kr.or.lgdlab.common.SearchCondition;
import kr.or.lgdlab.member.model.Member;
import kr.or.lgdlab.member.model.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    /**
     * 사용자 페이징 조회
     */
    @Test
    void userPagingList() {
        /*****GIVEN*****/
        SearchCondition searchCondition = new SearchCondition();

        /*****WHEN*****/
        Map<String, Object> resultMap = memberService.userPagingList(searchCondition);

        /*****THEN*****/
        assertThat(resultMap).containsKey("memberList");
        assertThat(resultMap).containsKey("pageNavigation");
        assertThat(resultMap).containsKey("memberTotalCount");
    }

    @Test
    void findById() {
        /*****GIVEN*****/

        /*****WHEN*****/
        MemberVo memberVo = memberService.findById("brown");

        /*****THEN*****/
        assertThat(memberVo.getMembernm()).isEqualTo("브라운");
    }

    @Test
    void save() {
        /*****GIVEN*****/
        Member member = new Member();
        member.setMemberid("newMemberId");
        member.setMembernm("신규멤버이름");

        /*****WHEN*****/
        memberService.save(member);

        /*****THEN*****/
    }
}