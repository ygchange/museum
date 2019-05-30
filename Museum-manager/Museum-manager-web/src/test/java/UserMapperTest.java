import com.museum.mapper.MemberInfoMapper;
import com.museum.pojo.MemberInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapperTest {
    @Test
    public void testUpdatePassword(){
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        MemberInfoMapper memberInfoMapper = (MemberInfoMapper) applicationContext.getBean("memberInfoMapper");
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        MemberInfo memberInfo=new MemberInfo();
        memberInfo.setId(30);
        memberInfo.setPassword(passwordEncoder.encode("123456"));
        memberInfoMapper.updateByPrimaryKey(memberInfo);
    }
}
