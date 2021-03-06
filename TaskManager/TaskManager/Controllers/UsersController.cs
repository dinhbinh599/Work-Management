﻿using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using NLog;
using System;
using System.Linq;
using TaskManager.Constants;
using TaskManager.Models;
using TaskManager.Models.Request;
using TaskManager.Services;
using TaskManager.ViewModels;

namespace TaskManager.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : BaseController
    {
        private readonly TaskManagerContext _context;
        private Logger _logger = LogManager.GetCurrentClassLogger();

        public UsersController(TaskManagerContext context, IUnitOfWork unitOfWork, IMapper mapper) : base(unitOfWork, mapper)
        {
            _context = context;
        }


        [HttpPost("all")]
        public IActionResult GetAllUser(GetUserRequest request)
        {
            try
            {
                var service = GetService<UserService>();
                var userList = service.GetAllUser(request);
                var result = MapToList<UserViewModel>(userList);
                return Ok(new ApiResult
                {
                    Message = ResultMessage.Success,
                    Data = result
                });
            }
            catch (Exception e)
            {
                _logger.Error(e, e.Message);
                return Error(new ApiResult
                {
                    Message = ResultMessage.Error
                });
            }

        }
        // GET: api/Users/5
        [HttpGet("{id}")]
        public IActionResult GetUser(int id)
        {
            try
            {
                var service = GetService<UserService>();
                var user = service.GetUserById(id);
                if (user == null)
                {
                    return NotFound(new ApiResult
                    {
                        Message = ResultMessage.NotFound
                    });
                }
                var result = MapTo<UserViewModel>(user);
                return Ok(new ApiResult
                {
                    Message = ResultMessage.Success,
                    Data = result
                });
            }
            catch (Exception e)
            {
                _logger.Error(e, e.Message);
                return Error(new ApiResult
                {
                    Message = ResultMessage.Error
                });
            }

        }

        // DELETE: api/Users/5
        [HttpDelete("{id}")]
        public IActionResult DeleteUser(int id)
        {
            try
            {
                var service = GetService<UserService>();
                var user = service.DeleteUser(id);
                _unitOfWork.SaveChanges();
                if (user == null)
                {
                    return BadRequest(new ApiResult
                    {
                        Message = ResultMessage.NotFound
                    });
                }
                return NoContent();
            }
            catch(Exception e){
                _logger.Error(e, e.Message);
                return Error(new ApiResult
                {
                    Message = ResultMessage.Error
                });
            }
        }

        // POST: api/Users/login
        [Route("login")]
        [HttpPost]
        public IActionResult Login(LoginRequest request)
        {
            try
            {
                var service = GetService<UserService>();
                var user = service.Authenticate(request.Username, request.Password);
                var result = MapTo<UserViewModel>(user);
                if (result == null)
                {
                    return NotFound(new ApiResult
                    {
                        Message = ResultMessage.NotSupported
                    });
                }
                return Ok(new ApiResult
                {
                    Data = result
                });
            }
            catch (Exception e)
            {
                _logger.Error(e, e.StackTrace);
                return BadRequest();
            }

        }
        [HttpPost]
        [Route("register")]
        public IActionResult Register(RequestRegister data)
        {
            var service = GetService<UserService>();
            var roleService = GetService<RoleService>();
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            var existedUser = service.GetUserByUsername(data.Username);
            if (existedUser != null)
            {
                return BadRequest(new ApiResult
                {
                    Message = "Username existed"
                });
            }
            User user = new User();
            user.Username = data.Username;
            user.PasswordHash = data.Password;
            var role = roleService.GetRoleByName(RoleName.USER);
            if (role != null)
            {
                user.RoleId = role.RoleId;
            }
            else
            {
                return BadRequest(new ApiResult
                {
                    Message = "Role not found"
                });
            }
            user.Fullname = data.Fullname;
            user.Email = data.Email;
            user.Phone = data.Phone;
            user.GroupId = null;
            user.ModifyTime = DateTime.Now;
            _context.User.Add(user);
            _context.SaveChanges();
            return Ok(new ApiResult
            {
                Message = "Ok"
            });
        }

        [HttpPut]
        [Route("Update")]
        public IActionResult UpdateUser(RequestUpdateUser data)
        {
            User user = _context.User.Where(c => (c.UserId == data.UserId)).FirstOrDefault();
            var roleService = GetService<RoleService>();
            user.Fullname = data.Fullname;
            user.Email = data.Email;
            user.Phone = data.Phone;
            var role = roleService.GetRoleByName(data.RoleName);
            if (role != null)
            {
                user.RoleId = role.RoleId;
            }
            else
            {
                return BadRequest(new ApiResult
                {
                    Message = "Role not found"
                });
            }
            if (data.GroupId != null)
            {
                var groupService = GetService<GroupService>();
                var group = groupService.GetGroupById(data.GroupId ?? 0);
                if (group == null)
                {
                    return BadRequest(new ApiResult
                    {
                        Message = ResultMessage.NotFound + " Group"
                    });
                }
                user.GroupId = data.GroupId;
            }
            user.ModifyTime = DateTime.Now;
            _context.Entry(user).State = EntityState.Modified;
            _context.SaveChanges();
            return Ok(new ApiResult
            {
                Message = "Ok"
            });
        }
    }
}
