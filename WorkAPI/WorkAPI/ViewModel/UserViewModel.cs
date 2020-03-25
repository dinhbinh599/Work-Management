using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkAPI.ViewModel
{
    public class UserViewModel
    {
        [JsonProperty("userId")]
        public int UserId { get; set; }
        [JsonProperty("username")]
        public string Username { get; set; }
        [JsonProperty("roleId")]
        public Nullable<int> RoleId { get; set; }
        [JsonProperty("fullName")]
        public string Fullname { get; set; }
        [JsonProperty("email")]
        public string Email { get; set; }
        [JsonProperty("groupId")]
        public Nullable<int> GroupId { get; set; }
    }
}