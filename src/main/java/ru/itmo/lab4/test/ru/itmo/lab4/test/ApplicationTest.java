package ru.itmo.lab4.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.*;
import ru.itmo.lab4.model.Graph;
import ru.itmo.lab4.model.Point;
import ru.itmo.lab4.service.PointsService;

import javax.validation.Valid;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@RestController
@RequestMapping("/api/points/")
public class ApplicationTest {
    @Autowired
    private PointsService pointsService;

    //    @Test
//    @Transactional
//    @Rollback(false)
    @GetMapping("{id}")
    public ResponseEntity<Point> getPoint(@PathVariable("id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Point p = pointsService.get(id);
        if(p == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Point>> getAllPoints(){
        List<Point> all = this.pointsService.getAll();
        if(all.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deletePoint(@PathVariable("id") Long id) {
        if(id == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Point p = pointsService.get(id);
        if(p==null){
      //            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      System.out.println("null bad");
        }
        pointsService.delete(id);
//        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Point> savePoint(@RequestBody @Valid Point point){
        HttpHeaders headers = new HttpHeaders();
        if(point == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        Graph graph = new Graph();
        point.setInArea(Graph.isInArea(point));
        this.pointsService.save(point);
        return new ResponseEntity<>(point, headers, HttpStatus.CREATED);
    }
}
