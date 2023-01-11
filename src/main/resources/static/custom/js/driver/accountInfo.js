<div class="row justify-content-center">
	<!-- 계좌 번호 입력 -->
	<div class="col col-md-6 mb-3">
		<label for="dAccountBank">계좌번호</label>
		<div class="input-group">
			<select id="dAccountBank" name="dAccountBank" th:field="*{dAccountBank}"
				class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" required>
				<option selected class="dropdown-item">은행명</option>
				<option class="dropdown-item" value="04">KB국민은행</option>
				<option class="dropdown-item" value="23">SC제일은행</option>
				<option class="dropdown-item" value="39">경남은행</option>
				<option class="dropdown-item" value="34">광주은행</option>
				<option class="dropdown-item" value="03">기업은행</option>
				<option class="dropdown-item" value="11">농협</option>
				<option class="dropdown-item" value="31">대구은행</option>
				<option class="dropdown-item" value="32">부산은행</option>
				<option class="dropdown-item" value="02">산업은행</option>
				<option class="dropdown-item" value="45">새마을금고</option>
				<option class="dropdown-item" value="07">수협</option>
				<option class="dropdown-item" value="88">신한은행</option>
				<option class="dropdown-item" value="48">신협</option>
				<option class="dropdown-item" value="81">외환은행</option>
				<option class="dropdown-item" value="20">우리은행</option>
				<option class="dropdown-item" value="71">우체국</option>
				<option class="dropdown-item" value="37">전북은행</option>
				<option class="dropdown-item" value="12">축협</option>
				<option class="dropdown-item" value="90">카카오뱅크</option>
				<option class="dropdown-item" value="89">케이뱅크</option>
				<option class="dropdown-item" value="81">하나은행</option>
				<option class="dropdown-item" value="27">한국씨티은행</option>
				<option class="dropdown-item" value="92">토스뱅크</option>
			</select> <input type="text" class="form-control" id="dAccountNum"
				name="c" aria-label="Text input with dropdown button"
				th:field="*{dAccountNum}" placeholder="계좌번호를 입력하세요" required>
				<div class="invalid-feedback">
					<span>계좌번호를 확인해주세요. </span><span>(숫자만 입력)</span>
				</div>
		</div>
	</div>
</div>