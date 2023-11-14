declare namespace API {
  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseLoginUserVO_ = {
    code?: number;
    data?: LoginUserVO;
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageUser_ = {
    code?: number;
    data?: PageUser_;
    message?: string;
  };

  type BaseResponsePageUserVO_ = {
    code?: number;
    data?: PageUserVO_;
    message?: string;
  };

  type BaseResponseUser_ = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BookAddReq = {
    bookAuthor?: string;
    bookAvatar?: string;
    bookBorrowPrice?: number;
    bookDesc?: string;
    bookIsbn?: string;
    bookName?: string;
    bookPrice?: number;
    bookPublisher?: string;
    bookStock?: number;
    bookTags?: string;
  };

  type BookUpdateReq = {
    bookAuthor?: string;
    bookAvatar?: string;
    bookBorrowPrice?: number;
    bookDesc?: string;
    bookIsbn?: string;
    bookName?: string;
    bookPrice?: number;
    bookPublisher?: string;
    bookStock?: number;
    bookTags?: string;
  };

  type delBookByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type DeleteRequest = {
    id?: number;
  };

  type getUserByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type LoginUserVO = {
    gender?: number;
    id?: number;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userDesc?: string;
    userEmail?: string;
    userNickname?: string;
  };

  type OrderItem = {
    asc?: boolean;
    column?: string;
  };

  type PageUser_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: User[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type User = {
    createTime?: string;
    deleted?: number;
    gender?: number;
    id?: number;
    updateTime?: string;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userDesc?: string;
    userEmail?: string;
    userNickname?: string;
    userPassword?: string;
    userPhone?: string;
    userRole?: number;
  };

  type UserAddReq = {
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userRole?: string;
  };

  type UserLoginReq = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserQueryReq = {
    current?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    userAccount?: string;
    userDesc?: string;
    userNickname?: string;
    userRole?: number;
  };

  type UserRegisterReq = {
    checkPassword?: string;
    userAccount?: string;
    userPassword?: string;
  };

  type UserUpdateReq = {
    id?: number;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserVO = {
    gender?: number;
    id?: number;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userDesc?: string;
    userEmail?: string;
    userNickname?: string;
    userPhone?: string;
    userRole?: number;
  };
}
