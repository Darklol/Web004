@Data is from lombok. It includes @Getter,@Setter,@toString,@hashCode,@equals

@Service,@Controller,@Repository are some kinds of @Component.There are almost the same.
Just tell the contain which part are them. And they let contain control the component

@RestController includes @Controller and @RequestBody.

@Autowired means it's a necessary field. You can use all that fields to create an obj.

@Valid means Spring will check the variant in the method to make sure they are not null or other bad types.

@Slf4j means We can use "log.info()" and "log.error()". To make the daily. It will be easier for us to find out what's happen.

@OneToMany and @ManyToOne are in order to build the relationship between the classes.

@JsonIgnore will let the Rest ignore that field.

@RequestMapping is for class. It will set the root for other paths which @GetMapping and @PostMapping set.

@GetMapping and @PostMapping are two different ways to send the data and build the endpoints. One uses method get, the other uses post.
And we can use @PathVariable to get them.
@DeleteMapping can delete the endpoints by method "Delete"

Package service is for communication between back-end and DB. They have two members respectively: PointsRepository and UserRepository.
And They extend JpaRepository. JpaRepository(A spring class) has own methods such as get, post, delete. So we can just use them.
