import akka.actor.Scheduler
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

object SchedulerService {
  def scheduleWithDelay(scheduler: Scheduler, delaySecondsStr: String)(implicit ec: ExecutionContext): Unit = {
    val delay = Duration(delaySecondsStr.toLong, java.util.concurrent.TimeUnit.SECONDS).asInstanceOf[FiniteDuration]
    //CWE-400
    //SINK
    scheduler.scheduleWithFixedDelay(delay, delay)(new Runnable { override def run(): Unit = () })
  }
}
