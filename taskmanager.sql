USE [TaskManager]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 4/13/2020 8:45:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[GroupId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[CreatedTime] [datetime] NULL,
	[Description] [nvarchar](200) NULL,
	[ModifyTime] [datetime] NULL,
 CONSTRAINT [PK__Group__149AF36A23595D1A] PRIMARY KEY CLUSTERED 
(
	[GroupId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 4/13/2020 8:45:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](256) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 4/13/2020 8:45:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[StatusId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](256) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[StatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Task]    Script Date: 4/13/2020 8:45:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Task](
	[TaskId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](200) NOT NULL,
	[SourceId] [int] NULL,
	[Description] [nvarchar](max) NOT NULL,
	[Report] [nvarchar](max) NULL,
	[ManagerReview] [nvarchar](max) NULL,
	[Mark] [int] NULL,
	[ReviewedTime] [datetime] NULL,
	[StartTime] [datetime] NULL,
	[EndTime] [datetime] NULL,
	[StatusId] [int] NOT NULL,
	[CreatedTime] [datetime] NOT NULL,
	[Creator] [nvarchar](50) NOT NULL,
	[HandlerId] [int] NULL,
	[ConfirmationImage] [nvarchar](max) NULL,
	[ModifyTime] [datetime] NULL,
 CONSTRAINT [PK__Task__7C6949B1DE115875] PRIMARY KEY CLUSTERED 
(
	[TaskId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 4/13/2020 8:45:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](256) NOT NULL,
	[PasswordHash] [nvarchar](max) NOT NULL,
	[RoleId] [int] NULL,
	[Fullname] [nvarchar](max) NOT NULL,
	[Email] [nvarchar](256) NULL,
	[GroupId] [int] NULL,
	[Phone] [nvarchar](15) NULL,
	[ModifyTime] [datetime] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Group] ON 

INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (6, N'C2C', CAST(N'2020-03-22T20:03:07.000' AS DateTime), N'haih', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (7, N'B2B', CAST(N'2020-03-22T20:03:46.000' AS DateTime), N'haih', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (8, N'C2C', CAST(N'2020-03-22T20:10:55.000' AS DateTime), N'hahah', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (11, N'ACC', CAST(N'2020-03-23T23:33:39.000' AS DateTime), N'Accounting Group', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (12, N'VNet', CAST(N'2020-03-25T00:50:52.000' AS DateTime), N'Vietnam Inter-networking', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (13, N'PRM', CAST(N'2020-03-25T09:34:49.000' AS DateTime), N'Mobile ', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (14, N'Test Group', CAST(N'2020-03-25T10:32:06.000' AS DateTime), N'', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (17, N'Tuan', CAST(N'2020-04-08T20:31:23.727' AS DateTime), N'123', NULL)
INSERT [dbo].[Group] ([GroupId], [Name], [CreatedTime], [Description], [ModifyTime]) VALUES (18, N'sdsd', CAST(N'2020-04-11T16:25:38.170' AS DateTime), N'ádasd', NULL)
SET IDENTITY_INSERT [dbo].[Group] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleId], [Name]) VALUES (1, N'Admin')
INSERT [dbo].[Role] ([RoleId], [Name]) VALUES (2, N'Manager')
INSERT [dbo].[Role] ([RoleId], [Name]) VALUES (3, N'User')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Status] ON 

INSERT [dbo].[Status] ([StatusId], [Name]) VALUES (2, N'Processing')
INSERT [dbo].[Status] ([StatusId], [Name]) VALUES (3, N'Finished')
INSERT [dbo].[Status] ([StatusId], [Name]) VALUES (5, N'Rejected')
INSERT [dbo].[Status] ([StatusId], [Name]) VALUES (6, N'Failed')
INSERT [dbo].[Status] ([StatusId], [Name]) VALUES (7, N'Overdue')
INSERT [dbo].[Status] ([StatusId], [Name]) VALUES (8, N'Not Started')
SET IDENTITY_INSERT [dbo].[Status] OFF
SET IDENTITY_INSERT [dbo].[Task] ON 

INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (2, N'Seminar', NULL, N'Do seminar', N'12312w', N'', NULL, CAST(N'2020-03-22T01:17:46.000' AS DateTime), CAST(N'2020-03-15T00:00:00.000' AS DateTime), CAST(N'2020-03-21T00:00:00.000' AS DateTime), 6, CAST(N'2020-03-12T00:00:00.000' AS DateTime), N'Baohg', NULL, NULL, NULL)
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (27, N'Task tử tế', NULL, N'Tử tế', N'', N'', NULL, CAST(N'2020-03-23T10:36:02.000' AS DateTime), CAST(N'2020-03-01T00:00:00.000' AS DateTime), CAST(N'2020-03-03T00:00:00.000' AS DateTime), 6, CAST(N'2020-03-19T00:00:00.000' AS DateTime), N'Eden', NULL, NULL, CAST(N'2020-03-23T10:36:02.017' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (40, N'Học Online', NULL, N'Online learning', N'Nah', N'Ok 5', 10, CAST(N'2020-04-12T09:45:39.000' AS DateTime), CAST(N'2020-03-22T00:00:00.000' AS DateTime), CAST(N'2020-03-23T00:00:00.000' AS DateTime), 3, CAST(N'2020-03-22T00:00:00.000' AS DateTime), N'Gia Bao', 15, N'-402020-03-23.jpg', CAST(N'2020-04-12T20:45:42.033' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (42, N'Tasky', NULL, N'tasky', N'', N'', NULL, CAST(N'2020-03-24T02:04:02.000' AS DateTime), CAST(N'2020-03-19T00:00:00.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), 3, CAST(N'2020-03-22T00:00:00.000' AS DateTime), N'Gia Bao', 15, NULL, CAST(N'2020-03-24T02:04:02.397' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (43, N'Brush teeth', NULL, N'brussh ', N'', N'', NULL, CAST(N'2020-03-25T01:51:33.000' AS DateTime), CAST(N'2020-03-25T00:00:00.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), 5, CAST(N'2020-03-23T00:00:00.000' AS DateTime), N'Gia Bao', 15, NULL, CAST(N'2020-03-25T01:51:33.427' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (50, N'Find 5 Way To Pass', NULL, N'bế tắc ', N'Khóc', N'Poor ', 10, CAST(N'2020-03-25T00:33:06.000' AS DateTime), CAST(N'1900-07-14T00:00:00.000' AS DateTime), CAST(N'2020-03-25T00:00:00.000' AS DateTime), 3, CAST(N'2020-03-25T00:00:00.000' AS DateTime), N'Gia Bao', 18, N'eden-50null.jpg', CAST(N'2020-03-25T00:33:06.040' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (51, N'Winner Dinner Chicken', NULL, N'Play Pubg', N'Top 10', N'cung dc', 9, CAST(N'2020-04-12T09:44:09.000' AS DateTime), CAST(N'2019-12-10T00:00:00.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), 3, CAST(N'2020-03-25T00:00:00.000' AS DateTime), N'Gia Bao', 39, N'-512020-03-26.jpg', CAST(N'2020-04-12T20:44:12.347' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (52, N'Find Girlfriend', NULL, N'Immediately', N'', N'', NULL, CAST(N'2020-03-25T01:45:13.000' AS DateTime), CAST(N'2020-03-27T00:00:00.000' AS DateTime), CAST(N'2020-03-28T00:00:00.000' AS DateTime), 6, CAST(N'2020-03-25T00:00:00.000' AS DateTime), N'Tran Nam Trung', 39, NULL, CAST(N'2020-03-25T01:45:13.160' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (54, N'Fix Bug', NULL, N'Hot fix', NULL, NULL, NULL, NULL, CAST(N'2020-03-25T00:00:00.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), 8, CAST(N'2020-03-25T01:47:14.080' AS DateTime), N'Tran Nam Trung', 18, NULL, NULL)
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (55, N'Complete Profile', NULL, N'User profile', N'Done nha', N'ok', 10, CAST(N'2020-04-12T07:26:42.000' AS DateTime), CAST(N'2020-03-25T00:00:00.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), 6, CAST(N'2020-03-25T00:00:00.000' AS DateTime), N'Nguyễn Phúc Hậu', 39, N'eden-552020-03-26.jpg', CAST(N'2020-04-12T18:26:44.960' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (57, N'Find New User', NULL, N'find new', NULL, NULL, NULL, NULL, CAST(N'2020-03-26T00:00:00.000' AS DateTime), CAST(N'2020-03-27T00:00:00.000' AS DateTime), 8, CAST(N'2020-03-25T09:26:55.080' AS DateTime), N'Gia Bao', 42, NULL, NULL)
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (59, N'Test task', NULL, N'tesy', N'Done nha', N'Cũng được', 10, CAST(N'2020-03-25T10:37:51.000' AS DateTime), CAST(N'2020-03-25T00:00:00.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), 3, CAST(N'2020-03-25T00:00:00.000' AS DateTime), N'Test User', 44, N'testuser-592020-03-26.jpg', CAST(N'2020-03-25T10:37:51.967' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (61, N'Task manager', NULL, N'tmanaager', N'', N'', NULL, CAST(N'2020-03-25T10:38:05.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), CAST(N'2020-03-29T00:00:00.000' AS DateTime), 2, CAST(N'2020-03-25T00:00:00.000' AS DateTime), N'Test manager', 44, NULL, CAST(N'2020-03-25T10:38:06.377' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (62, N'Fail task', NULL, N'fail', NULL, NULL, NULL, NULL, CAST(N'2020-03-25T00:00:00.000' AS DateTime), CAST(N'2020-03-28T00:00:00.000' AS DateTime), 8, CAST(N'2020-03-25T10:41:12.020' AS DateTime), N'Test manager', 45, NULL, NULL)
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (64, N'lee sin', NULL, N'haha', NULL, NULL, NULL, NULL, CAST(N'2020-04-09T00:00:00.000' AS DateTime), CAST(N'2020-04-11T00:00:00.000' AS DateTime), 8, CAST(N'2020-04-09T23:19:15.747' AS DateTime), N'', 18, NULL, NULL)
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (65, N'hahaah', NULL, N'sds', NULL, NULL, NULL, NULL, CAST(N'2020-04-01T00:00:00.000' AS DateTime), CAST(N'2020-04-25T00:00:00.000' AS DateTime), 8, CAST(N'2020-04-09T23:20:14.800' AS DateTime), N'', 44, NULL, NULL)
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (66, N'a', NULL, N'sdsd', N'sdsdsd', N'', NULL, CAST(N'2020-04-12T07:08:08.000' AS DateTime), CAST(N'2020-04-01T00:00:00.000' AS DateTime), CAST(N'2020-04-13T00:00:00.000' AS DateTime), 2, CAST(N'2020-04-12T00:00:00.000' AS DateTime), N'', 43, NULL, CAST(N'2020-04-12T18:08:10.793' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (67, N'Find 10 ways to pass', NULL, N'That la be tac qua di', N'That la buon qua di', N'That la buon lam sao ', 10, CAST(N'2020-04-12T07:22:56.000' AS DateTime), CAST(N'2020-04-01T00:00:00.000' AS DateTime), CAST(N'2020-04-13T00:00:00.000' AS DateTime), 3, CAST(N'2020-04-12T00:00:00.000' AS DateTime), N'', 39, NULL, CAST(N'2020-04-12T18:22:58.533' AS DateTime))
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (68, N'Complete Profile', 55, N'User profile', NULL, NULL, NULL, NULL, CAST(N'2020-03-25T00:00:00.000' AS DateTime), CAST(N'2020-03-26T00:00:00.000' AS DateTime), 8, CAST(N'2020-04-12T18:27:01.867' AS DateTime), N'', 39, NULL, NULL)
INSERT [dbo].[Task] ([TaskId], [Name], [SourceId], [Description], [Report], [ManagerReview], [Mark], [ReviewedTime], [StartTime], [EndTime], [StatusId], [CreatedTime], [Creator], [HandlerId], [ConfirmationImage], [ModifyTime]) VALUES (69, N'Find Girlfriend', NULL, N'Immediately', NULL, NULL, NULL, NULL, CAST(N'2020-04-15T00:00:00.000' AS DateTime), CAST(N'2020-04-16T00:00:00.000' AS DateTime), 8, CAST(N'2020-04-12T18:29:28.200' AS DateTime), N'', 18, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Task] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (15, N'Tuan', N'123', 1, N'Tuan', N'phamtuan@gmail.com', NULL, N'090154341', CAST(N'2020-04-04T17:31:17.923' AS DateTime))
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (18, N'tnt', N'1', 2, N'Tran Nam Trung', N'trungtran@gmail.com', 6, N'0901547767', CAST(N'2020-03-23T03:08:31.170' AS DateTime))
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (19, N'bhg', N'1', 3, N'Bao Hoang', N'ggg@gmail.com', NULL, N'0901', CAST(N'2020-03-23T22:30:45.117' AS DateTime))
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (39, N'phg', N'1', 3, N'Nguyễn Phúc Hậu', N'haunguyen@gmail.com', 6, N'+84901547767', CAST(N'2020-03-25T01:33:42.850' AS DateTime))
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (42, N'dto', N'1', 2, N'Võ Đông Triều', N'trieuvo@gmail.comm', NULL, N'+84901547767', CAST(N'2020-03-25T09:27:25.347' AS DateTime))
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (43, N'dta', N'1', 3, N'Dương Triều Anh', N'trieuanhduong@gmail.com', NULL, N'0901547767', NULL)
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (44, N'testuser', N'1', 3, N'Test User', N'giabaohoang9199@gmail.com', 14, N'+84901547767', CAST(N'2020-03-25T10:32:34.073' AS DateTime))
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (45, N'testmanager', N'1', 2, N'Test manager', N'giabaohoang9199@gmail.com', 14, N'+84901547767', CAST(N'2020-03-25T10:35:19.903' AS DateTime))
INSERT [dbo].[User] ([UserId], [Username], [PasswordHash], [RoleId], [Fullname], [Email], [GroupId], [Phone], [ModifyTime]) VALUES (78, N'Bao', N'123', 3, N'BaoGia', N'BaoGia@gmail.com', NULL, N'1234567890', CAST(N'2020-03-30T16:47:55.350' AS DateTime))
SET IDENTITY_INSERT [dbo].[User] OFF
ALTER TABLE [dbo].[Task]  WITH CHECK ADD  CONSTRAINT [FK_Task_Status] FOREIGN KEY([StatusId])
REFERENCES [dbo].[Status] ([StatusId])
GO
ALTER TABLE [dbo].[Task] CHECK CONSTRAINT [FK_Task_Status]
GO
ALTER TABLE [dbo].[Task]  WITH CHECK ADD  CONSTRAINT [FK_Task_Task] FOREIGN KEY([SourceId])
REFERENCES [dbo].[Task] ([TaskId])
GO
ALTER TABLE [dbo].[Task] CHECK CONSTRAINT [FK_Task_Task]
GO
ALTER TABLE [dbo].[Task]  WITH CHECK ADD  CONSTRAINT [FK_Task_User] FOREIGN KEY([HandlerId])
REFERENCES [dbo].[User] ([UserId])
GO
ALTER TABLE [dbo].[Task] CHECK CONSTRAINT [FK_Task_User]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Group] FOREIGN KEY([GroupId])
REFERENCES [dbo].[Group] ([GroupId])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Group]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Role] ([RoleId])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
