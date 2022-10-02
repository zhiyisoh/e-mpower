// package empower.empower.log;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;


// @RestController
// public class LogController {
//     private LogService logService;

//     public LogController(LogService ls){
//         this.logService = ls;
//     }

//     /**
//      * List all books in the system
//      * @return list of all books
//      */
//     @GetMapping("/logging")
//     public List<Log> getLogs(){
//         return logService.listLogs();
//     }

//     /**
//      * Search for book with the given id
//      * If there is no book with the given "id", throw a BookNotFoundException
//      * @param id
//      * @return book with the given id
//      */
//     @GetMapping("/logging/{id}")
//     public Log getLog(@PathVariable Long id){
//         Log log = logService.getLog(id);

//         // Need to handle "book not found" error using proper HTTP status code
//         // In this case it should be HTTP 404
//         if(log == null) throw new LogNotFoundException(id);
//         return logService.getLog(id);

//     }
//     /**
//      * Add a new book with POST request to "/books"
//      * Note the use of @RequestBody
//      * @param book
//      * @return list of all books
//      */
//     @ResponseStatus(HttpStatus.CREATED)
//     @PostMapping("/logging")
//     public Log addLog(@RequestBody Log log){
//         return logService.addLog(log);
//     }

//     /**
//      * If there is no book with the given "id", throw a BookNotFoundException
//      * @param id
//      * @param newLogInfo
//      * @return the updated, or newly added book
//      */
//     @PutMapping("/logging/{id}")
//     public Log updateLog(@PathVariable Long id, @RequestBody Log newLogInfo){
//         Log log = logService.updateLog(id, newLogInfo);
//         if(log == null) throw new LogNotFoundException(id);
        
//         return log;
//     }

//     /**
//      * Remove a book with the DELETE request to "/books/{id}"
//      * If there is no book with the given "id", throw a LogNotFoundException
//      * @param id
//      */
//     @DeleteMapping("/logging/{id}")
//     public void deleteLog(@PathVariable Long id){
//         if(logService.deleteLog(id) == 0) throw new LogNotFoundException(id);
//     }
// }