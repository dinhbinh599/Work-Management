﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TaskManager.Models.Request
{
    public class RequestUpdateUser
    {
        public int UserId { get; set; }
        public string Fullname { get; set; }
        public string Email { get; set; }
        public string Phone { get; set; }
        public string RoleName { get; set; }
    }
}
