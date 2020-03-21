using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Net.Http.Headers;
using System.Data.Entity;
using WorkAPI;

namespace WorkAPI.Controllers
{
    [RoutePrefix("api/tasks")]
    public class TasksController : ApiController
    {
        [HttpGet]
        public IHttpActionResult GetTasksBaseOnHandlerID(string id)
        {

            try
            {

                IList<Task> tasks = null;
                Int16 intid = Int16.Parse(id);
                using (var ctx = new TaskManagerEntities())
                {

                    tasks = ctx.Tasks.Where(s => s.HandlerId == intid).ToList<Task>();

                }
                if (tasks.Count == 0)
                {

                    return NotFound();
                }
                return Ok(tasks);
            }
            catch (Exception e)
            {
                return InternalServerError();
            }

        }
    }
}