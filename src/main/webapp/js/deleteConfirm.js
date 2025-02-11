(function() {
	const deleteButtons = document.querySelectorAll(".delete-button");
	
	deleteButtons.forEach(function(deleteButton){
		setDeleteConfirmEvent(deleteButton)
	})
	
	
	/**
	 * ボタンに削除確認ダイアログを表示するイベントをセットするメソッド
	 * 
	 * @param イベント
	 */
	function setDeleteConfirmEvent (deleteButton) {
		
		deleteButton.addEventListener('click', function(event) {
		let result = window.confirm('本当に削除しますか？もとには戻せません。');
		
			if(!result) { //キャンセルを押した場合ファームの送信をキャンセル
				event.preventDefault();
			}
		});
	}
})()
