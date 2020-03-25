using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Web.Http;
using System.Web.Http.Description;
using WorkAPI.Models;
using WorkAPI.ViewModel;

namespace WorkAPI.Controllers
{

    [RoutePrefix("api/users")]
    public class UsersController : ApiController
    {
        private TaskManagerEntities db = new TaskManagerEntities();


            [HttpPost]
            [Route("login")]
            public IHttpActionResult Login([FromBody]LoginRequest data)
            {
                try
                {
                       var result = new UserViewModel() ;
                    IList<User> listUser=null;
                    using (var ctx = new TaskManagerEntities())
                    {
                        if (data.Username != null && data.Username != "" && data.Password != null && data.Password != "")
                        {
                            listUser = ctx.Users.Where(c => (c.Username.Equals(data.Username) && c.PasswordHash.Equals(data.Password))).ToList<User>();
                        }
                    }
                    if (listUser.Count ==  0)
                    {
                        return NotFound();
                    }

                result.UserId = listUser[0].UserId;
                result.Username = listUser[0].Username;
                result.RoleId = listUser[0].RoleId;
                result.Fullname = listUser[0].Fullname;
                result.Email = listUser[0].Email;

                return Ok(new ApiResult {
                    Data = result,
                    }
                        );
                }
                catch (Exception e)
                {
                    return BadRequest();
                }
            }
        [HttpPost]
        [Route("register")]
        public IHttpActionResult Register([FromBody]RequestRegister data)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            User user = new User();
            user.Username = data.Username;
            user.PasswordHash = data.Password;
            user.RoleId = data.RoleId;
            user.Fullname = data.Fullname;
            user.Email = data.Email;
            user.GroupId = data.GroubId;
            var result = db.Users.Add(user);
            db.SaveChanges();
            return Ok(result);
        }
        [HttpPut]
        [Route("Update")]
        public IHttpActionResult UpdateUser([FromBody]RequestUpdateUser data)
        {
            User user = db.Users.Where(c => (c.UserId == data.Id)).FirstOrDefault();
            user.Username = data.Username;
            user.PasswordHash = data.Password;
            user.RoleId = data.RoleId;
            user.Fullname = data.Fullname;
            user.Email = data.Email;
            user.GroupId = data.GroubId;
            db.Entry(user).State = EntityState.Modified;
            return Ok(db.SaveChanges());
        }


        // GET: api/Users
        public IQueryable<User> GetUsers()
        {
            return db.Users;
        }

        // GET: api/Users/5
        [ResponseType(typeof(User))]
        public IHttpActionResult GetUser(int id)
        {
            User user = db.Users.Find(id);
            if (user == null)
            {
                return NotFound();
            }

            return Ok(user);
        }

        // PUT: api/Users/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUser(int id, User user)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != user.UserId)
            {
                return BadRequest();
            }

            db.Entry(user).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UserExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Users
        [ResponseType(typeof(User))]
        public IHttpActionResult PostUser(User user)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Users.Add(user);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = user.UserId }, user);
        }

        // DELETE: api/Users/5
        [ResponseType(typeof(User))]
        public IHttpActionResult DeleteUser(int id)
        {
            User user = db.Users.Find(id);
            if (user == null)
            {
                return NotFound();
            }

            db.Users.Remove(user);
            db.SaveChanges();

            return Ok(user);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool UserExists(int id)
        {
            return db.Users.Count(e => e.UserId == id) > 0;
        }
    }
}