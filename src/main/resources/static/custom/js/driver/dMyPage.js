function editReview(rIdx) {
  (async () => {
    const { value: content } = await Swal.fire({
      title: '수정할 리뷰를 입력하세요.',
      input: 'text',
    })
    if (content) {
      $.ajax({
        url: "/driver/driverCarpool/review/edit",
        type: "PUT",
        data: { rIdx: rIdx, content: content },
        success: function (data, status) {
          if (status === "success") {
            console.log("완료");
            window.location.reload();
          }
        },
      });
    }
  })()
}

function deleteReview(rIdx) {
  Swal.fire({
    title: "리뷰를 삭제하시겠습니까?",
    icon: "warning",

    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
    confirmButtonColor: "#3085d6", // confrim 버튼 색깔 지정
    cancelButtonColor: "#d33", // cancel 버튼 색깔 지정
    confirmButtonText: "승인", // confirm 버튼 텍스트 지정
    cancelButtonText: "취소", // cancel 버튼 텍스트 지정

    reverseButtons: false, // 버튼 순서 거꾸로
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url: "/driver/driverCarpool/review/delete",
        type: "DELETE",
        data: { rIdx: rIdx },
        success: function (data, status) {
          if (status === "success") {
            console.log("완료");
            // $('#divReloadLayer').load(location.href + ' #divReloadLayer');
          }
        },
      });
    }
  })
}