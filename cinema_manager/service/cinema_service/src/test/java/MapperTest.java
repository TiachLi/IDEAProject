import com.atguigu.CinemaServiceApplication;
import com.atguigu.entity.Movie;
import com.atguigu.entity.PlayList;
import com.atguigu.entity.vo.MovieVo;
import com.atguigu.entity.vo.SeatVo;
import com.atguigu.entity.vo.SelectedSeatVo;
import com.atguigu.entity.vo.TicketInfoVo;
import com.atguigu.mapper.MovieMapper;
import com.atguigu.mapper.PlayListMapper;
import com.atguigu.mapper.SeatMapper;
import com.atguigu.mapper.TicketMapper;
import com.atguigu.service.PlayListService;
import com.atguigu.service.SeatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CinemaServiceApplication.class)
@RunWith(SpringRunner.class)
public class MapperTest {

    @Resource
    MovieMapper movieMapper;
    @Resource
    SeatMapper seatMapper;
    @Resource
    TicketMapper ticketMapper;
    @Resource
    PlayListMapper playListMapper;
    @Resource(name = "seatService")
    SeatService seatService;
    @Resource
    PlayListService playListService;
    @Test
    public void test1(){
/*        Movie movie = new Movie();
        movie.setMovieId(10);
        movie.setMovieType("测试类型");
        movieMapper.updateById(movie);*/
    }

    @Test
    public void test2(){

    }
}
