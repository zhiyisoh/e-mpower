package empower.empower.log;

import java.util.List;


public interface LogService {
    List<Log> listLogs();
    Log getLog(Long id);

    /**
     * Return the newly added log
     */
    Log addLog(Log log);

    /** 
     * Return the updated log
     * @param id
     * @param log
     * @return
     */
    Log updateLog(Long id, Log log);

    /**
     * Return status of the delete
     * If it's 1: the book has been removed
     * If it's 0: the book does not exist
     * @param id
     * @return 
     */
    int deleteLog(Long id);
}